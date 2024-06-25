package meetween.backend.appointment.domain;

import jakarta.persistence.LockModeType;
import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppointmentUserRepository extends JpaRepository<AppointmentUser, Long> {

    @EntityGraph(attributePaths = {"appointment", "appointment.category"})
    Page<AppointmentUser> findAllByMember(Member member, PageRequest pageRequest);

    Optional<AppointmentUser> findByAppointmentAndMember(Appointment appointment, Member member);

    default AppointmentUser getByMemberAndAppointment(final Member member, final Appointment appointment) {
        return findByAppointmentAndMember(appointment, member)
                .orElseThrow(NoExistAppointmentUserException::new);
    }

    boolean existsByAppointmentAndMember(Appointment appointment, Member member);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select au from AppointmentUser au where au.appointment = :appointment")
    List<AppointmentUser> findByAppointmentWithOptimisticLock(Appointment appointment);
}
