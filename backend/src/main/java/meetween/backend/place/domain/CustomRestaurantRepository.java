package meetween.backend.place.domain;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;

@Component
public class CustomRestaurantRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomRestaurantRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void saveAllRestaurantsBatch(Collection<Restaurant> restaurants) {
        String sql = "INSERT IGNORE INTO restaurant (id, name, address, type, latitude, longitude, created_at, updated_at)" +
                " VALUES (:restaurantId, :name, :address, :type, :latitude, :longitude, :createdAt, :updatedAt)";
        namedParameterJdbcTemplate.batchUpdate(sql, restaurantSqlParameterSource(restaurants));
    }

    private MapSqlParameterSource[] restaurantSqlParameterSource(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::changeToSqlParameterSource)
                .toArray(MapSqlParameterSource[]::new);
    }

    private MapSqlParameterSource changeToSqlParameterSource(Restaurant restaurant) {
        LocalDateTime now = LocalDateTime.now();
        return new MapSqlParameterSource()
                .addValue("restaurantId", restaurant.getId())
                .addValue("name", restaurant.getName())
                .addValue("address", restaurant.getAdress())
                .addValue("type", restaurant.getType())
                .addValue("latitude", restaurant.getLatitude())
                .addValue("longitude", restaurant.getLongitude())
                .addValue("createdAt", now)
                .addValue("updatedAt", now);
    }
}
