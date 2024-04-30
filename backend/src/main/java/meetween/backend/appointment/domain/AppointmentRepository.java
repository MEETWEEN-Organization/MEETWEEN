package meetween.backend.appointment.domain;

import meetween.backend.appointment.exception.NoExistAppointmentException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByInviteCode(Long inviteCode);

    boolean existsByInviteCode(Long inviteCode);

    default Appointment getByInviteCode(final Long inviteCode) {
        return findByInviteCode(inviteCode)
                .orElseThrow(NoExistAppointmentException::new);
    }



//    List<Appointment> findByCategory(Category category);
//    @Query("SELECT DISTINCT a FROM Appointment a " +
//            "JOIN a.category c " +
//            "JOIN a.appointmentUsers au " +
//            "WHERE au.user = :user " +
//            "AND c.name = :categoryName")
//    List<Appointment> findByUserAndCategoryName(@Param("user") User user, @Param("categoryName") String categoryName);
}
