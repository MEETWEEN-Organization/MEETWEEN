package meetween.backend.place.domain;

import meetween.backend.place.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "WHERE r.latitude >= :minLatitude AND r.latitude <= :maxLatitude " +
            "AND r.longitude >= :minLongitude AND r.longitude <= :maxLongitude"
    )
    List<Restaurant> findAllByLatitudeAndLongitudeBetween(@Param("minLatitude") BigDecimal minLatitude,
                                                                          @Param("maxLatitude") BigDecimal maxLatitude,
                                                                          @Param("minLongitude") BigDecimal minLongitude,
                                                                          @Param("maxLongitude") BigDecimal maxLongitude);
}
