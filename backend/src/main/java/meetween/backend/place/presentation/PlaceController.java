package meetween.backend.place.presentation;

import meetween.backend.place.application.PlaceService;
import meetween.backend.place.dto.response.RestaurantResponse;
import meetween.backend.place.dto.request.PlacesByLocationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/place")
@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/near")
    public ResponseEntity<RestaurantResponse> getNearRestaurants(@RequestBody final PlacesByLocationRequest request) {
        RestaurantResponse response = placeService.getNearRestaurants(request);
        return ResponseEntity.ok().body(response);
    }

}
