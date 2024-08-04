package meetween.backend.appointment.domain;

import meetween.backend.appointment.exception.NoExistAppointmentException;
import meetween.backend.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where a.inviteCode.code = :inviteCode")
    Optional<Appointment> findByInviteCode(Long inviteCode);

    default Appointment getByInviteCode(final Long inviteCode) {
        return findByInviteCode(inviteCode)
                .orElseThrow(NoExistAppointmentException::new);
    }

    default Appointment getById(final Long id) {
        return findById(id)
                .orElseThrow(NoExistAppointmentException::new);
    }

    @Query("SELECT DISTINCT a FROM Appointment a " +
            "JOIN FETCH a.category c " +
            "JOIN FETCH a.inviteCode ic " +
            "JOIN a.appointmentUsers au " +
            "WHERE au.member = :member " +
            "AND c.name = :categoryName")
    Page<Appointment> findByUserAndCategoryName(@Param("member") Member member, @Param("categoryName") String categoryName, PageRequest pageRequest);
}