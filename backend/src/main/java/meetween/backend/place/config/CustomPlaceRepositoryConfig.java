package meetween.backend.place.config;

import meetween.backend.place.domain.CustomPlaceRepository;
import meetween.backend.place.domain.entity.Bakery;
import meetween.backend.place.domain.entity.Cafe;
import meetween.backend.place.domain.entity.Restaurant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class CustomPlaceRepositoryConfig {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomPlaceRepositoryConfig(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Bean
    public CustomPlaceRepository<Restaurant> customRestaurantRepository() {
        return new CustomPlaceRepository<>(namedParameterJdbcTemplate, Restaurant.class);
    }

    @Bean
    public CustomPlaceRepository<Cafe> customCafeRepository() {
        return new CustomPlaceRepository<>(namedParameterJdbcTemplate, Cafe.class);
    }

    @Bean
    public CustomPlaceRepository<Bakery> customBakeryRepository() {
        return new CustomPlaceRepository<>(namedParameterJdbcTemplate, Bakery.class);
    }
}
