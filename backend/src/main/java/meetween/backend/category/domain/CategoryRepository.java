package meetween.backend.category.domain;

import org.springframework.data.jpa.repository.JpaRepository;

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
