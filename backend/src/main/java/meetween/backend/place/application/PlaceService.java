package meetween.backend.place.application;

import meetween.backend.global.config.cache.CacheConfig;
import meetween.backend.place.domain.coordinate.Coordinate;
import meetween.backend.member.domain.MemberRepository;
import meetween.backend.place.domain.entity.Restaurant;
import meetween.backend.place.domain.RestaurantRepository;
import meetween.backend.place.dto.response.RestaurantResponse;
import meetween.backend.place.dto.response.RestaurantSpecificResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class PlaceService {

    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    public PlaceService(RestaurantRepository restaurantRepository, MemberRepository memberRepository) {
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
    }

    @Cacheable(value = CacheConfig.RESTAURANT, key = "#latitude+#longitude")
    public RestaurantResponse getNearRestaurants(final BigDecimal latitude, final BigDecimal longitude, final BigDecimal latitudeDelta, final BigDecimal longitudeDelta) {
        Coordinate coordinate = Coordinate.of(latitude, longitude, latitudeDelta, longitudeDelta);

        List<Restaurant> restaurants = restaurantRepository.findAllByLatitudeAndLongitudeBetween(
                coordinate.getMinLatitude(),
                coordinate.getMaxLatitude(),
                coordinate.getMinLongitude(),
                coordinate.getMaxLongitude()
        );

        List<RestaurantSpecificResponse> integratedRestaurants = toIntegratedRestaurants(restaurants);

        return new RestaurantResponse(integratedRestaurants);
    }

    private List<RestaurantSpecificResponse> toIntegratedRestaurants(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantSpecificResponse::new)
                .collect(Collectors.toList());
    }
}
