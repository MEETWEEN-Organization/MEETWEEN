package meetween.backend.place.domain;

import meetween.backend.place.domain.entity.Place;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.Collection;

public class CustomPlaceRepository<T extends Place> {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Class<T> type;

    public CustomPlaceRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, Class<T> type) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.type = type;
    }

    public void saveAllPlacesBatch(Collection<T> places) {
        String tableName = type.getSimpleName().toLowerCase();
        String sql = String.format("INSERT IGNORE INTO %s (id, name, address, type, latitude, longitude, created_at, updated_at)" +
                " VALUES (:id, :name, :address, :type, :latitude, :longitude, :createdAt, :updatedAt)", tableName);
        namedParameterJdbcTemplate.batchUpdate(sql, placeSqlParameterSource(places));
    }

    private MapSqlParameterSource[] placeSqlParameterSource(Collection<T> places) {
        return places.stream()
                .map(this::changeToSqlParameterSource)
                .toArray(MapSqlParameterSource[]::new);
    }

    private MapSqlParameterSource changeToSqlParameterSource(T place) {
        LocalDateTime now = LocalDateTime.now();
        return new MapSqlParameterSource()
                .addValue("id", place.getId())
                .addValue("name", place.getName())
                .addValue("address", place.getAddress())
                .addValue("type", place.getType())
                .addValue("latitude", place.getLatitude())
                .addValue("longitude", place.getLongitude())
                .addValue("createdAt", now)
                .addValue("updatedAt", now);
    }
}
