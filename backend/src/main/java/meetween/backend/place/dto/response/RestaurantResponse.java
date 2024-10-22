package meetween.backend.place.dto.response;

import java.util.List;

public class RestaurantResponse {

    List<RestaurantSpecificResponse> specificResponses;

    public RestaurantResponse(List<RestaurantSpecificResponse> specificResponses) {
        this.specificResponses = specificResponses;
    }

    public List<RestaurantSpecificResponse> getSpecificResponses() {
        return specificResponses;
    }
}
