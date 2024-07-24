package meetween.backend.place.application;

import meetween.backend.place.domain.Coordinate;
import meetween.backend.member.domain.MemberRepository;
import meetween.backend.place.domain.RestaurantRepository;
import meetween.backend.place.dto.RestaurantResponse;
import meetween.backend.place.dto.RestaurantSpecificResponse;
import meetween.backend.place.dto.RestaurantsByLocationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PlaceService {

    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    public PlaceService(RestaurantRepository restaurantRepository, MemberRepository memberRepository) {
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
    }

    public RestaurantResponse getNearRestaurants(final RestaurantsByLocationRequest request) {
        Coordinate coordinate = Coordinate.of(request.getLatitude(), request.getLongitude(), request.getLatitudeDelta(), request.getLongitudeDelta());

        List<RestaurantSpecificResponse> restaurants = restaurantRepository.findAllByLatitudeAndLongitudeBetween(
                coordinate.getMinLatitude(),
                coordinate.getMaxLatitude(),
                coordinate.getMinLongitude(),
                coordinate.getMaxLongitude()
        );

        return new RestaurantResponse(restaurants);
    }
}
