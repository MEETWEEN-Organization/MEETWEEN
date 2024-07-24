package meetween.backend.place.presentation;

import meetween.backend.place.application.PlaceService;
import meetween.backend.place.dto.RestaurantResponse;
import meetween.backend.place.dto.RestaurantsByLocationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/place")
@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("near")
    public ResponseEntity<RestaurantResponse> getNearRestaurants(@RequestBody final RestaurantsByLocationRequest request) {
        RestaurantResponse response = placeService.getNearRestaurants(request);
        return ResponseEntity.ok().body(response);
    }

}
