package meetween.backend.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.Restaurant;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantInfoRequest {

    @JsonProperty("list_total_count")
    private int totalCount;
    @JsonProperty("row")
    private List<RestaurantApiRequest> items;
    public List<Restaurant> toRestaurants() {
        return items.stream()
                .map(RestaurantApiRequest::toRestaurant)
                .toList();
    }

    public int getTotalCount() {
        return totalCount;
    }
}
