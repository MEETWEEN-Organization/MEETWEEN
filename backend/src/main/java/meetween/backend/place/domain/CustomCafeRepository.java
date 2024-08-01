package meetween.backend.place.domain;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;

@Component
public class CustomCafeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomCafeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void saveAllCafesBatch(Collection<Cafe> cafes) {
        String sql = "INSERT IGNORE INTO cafe (id, name, address, type, latitude, longitude, created_at, updated_at)" +
                " VALUES (:cafeId, :name, :address, :type, :latitude, :longitude, :createdAt, :updatedAt)";
        namedParameterJdbcTemplate.batchUpdate(sql, cafeSqlParameterSource(cafes));
    }

    private MapSqlParameterSource[] cafeSqlParameterSource(Collection<Cafe> cafes) {
        return cafes.stream()
                .map(this::changeToSqlParameterSource)
                .toArray(MapSqlParameterSource[]::new);
    }

    private MapSqlParameterSource changeToSqlParameterSource(Cafe cafe) {
        LocalDateTime now = LocalDateTime.now();
        return new MapSqlParameterSource()
                .addValue("cafeId", cafe.getId())
                .addValue("name", cafe.getName())
                .addValue("address", cafe.getAdress())
                .addValue("type", cafe.getType())
                .addValue("latitude", cafe.getLatitude())
                .addValue("longitude", cafe.getLongitude())
                .addValue("createdAt", now)
                .addValue("updatedAt", now);
    }
}
