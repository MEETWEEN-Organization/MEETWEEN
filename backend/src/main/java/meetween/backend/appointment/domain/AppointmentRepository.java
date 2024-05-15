package meetween.backend.appointment.domain;

import meetween.backend.appointment.exception.NoExistAppointmentException;
import meetween.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByInviteCode(Long inviteCode);

    boolean existsByInviteCode(Long inviteCode);

    default Appointment getByInviteCode(final Long inviteCode) {
        return findByInviteCode(inviteCode)
                .orElseThrow(NoExistAppointmentException::new);
    }

    @Query("SELECT DISTINCT a FROM Appointment a " +
            "JOIN a.category c " +
            "JOIN a.appointmentUsers au " +
            "WHERE au.member = :member " +
            "AND c.name = :categoryName")
    List<Appointment> findByUserAndCategoryName(@Param("member") Member member, @Param("categoryName") String categoryName);
}