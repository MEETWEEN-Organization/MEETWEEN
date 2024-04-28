package meetween.backend.category.domain;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    List<Category> findByName(String name);
//
//    @Query("SELECT DISTINCT a FROM Category c " +
//            "JOIN a.category c " +
//            "JOIN a.appointmentUsers au " +
//            "WHERE au.user = :user " +
//            "AND c.name = :categoryName")
//    List<Appointment> findByUserAndCategoryName(@Param("user") User user, @Param("categoryName") String categoryName);
}
