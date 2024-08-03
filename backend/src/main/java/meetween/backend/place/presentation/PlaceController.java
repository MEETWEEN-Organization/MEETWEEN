package meetween.backend.place.presentation;

import meetween.backend.place.application.PlaceService;
import meetween.backend.place.dto.request.api.RestaurantRequest;
import meetween.backend.place.dto.response.RestaurantResponse;
import meetween.backend.place.dto.request.PlacesByLocationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequestMapping("/place")
@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/near")
    public ResponseEntity<RestaurantResponse> getNearRestaurants(@RequestParam("latitude") BigDecimal latitude,
                                                                 @RequestParam("longitude") BigDecimal longitude,
                                                                 @RequestParam("latitudeDelta") BigDecimal latitudeDelta,
                                                                 @RequestParam("longitudeDelta") BigDecimal longitudeDelta
    ) {
        RestaurantResponse response = placeService.getNearRestaurants(latitude, longitude, latitudeDelta, longitudeDelta);
        return ResponseEntity.ok().body(response);
    }

}
