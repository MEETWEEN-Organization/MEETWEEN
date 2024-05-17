package meetween.backend.appointment.domain;

import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentUserRepository extends JpaRepository<AppointmentUser, Long> {
    List<AppointmentUser> findAllByMember(Member member);

    Optional<AppointmentUser> findByAppointmentAndMember(Appointment appointment, Member member);

    default AppointmentUser getByMemberAndAppointment(final Member member, final Appointment appointment){
        return findByAppointmentAndMember(appointment, member)
                .orElseThrow(NoExistAppointmentUserException::new);
    }
}
