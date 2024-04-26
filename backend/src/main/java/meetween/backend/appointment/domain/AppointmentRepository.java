package meetween.backend.appointment.domain;

import meetween.backend.category.domain.Category;
import meetween.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
//    List<Appointment> findByCategory(Category category);
//    @Query("SELECT DISTINCT a FROM Appointment a " +
//            "JOIN a.category c " +
//            "JOIN a.appointmentUsers au " +
//            "WHERE au.user = :user " +
//            "AND c.name = :categoryName")
//    List<Appointment> findByUserAndCategoryName(@Param("user") User user, @Param("categoryName") String categoryName);
}
