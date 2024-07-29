package meetween.backend.place.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.Restaurant;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantRequest {

    @JsonProperty("LOCALDATA_072404")
    private PlaceInfoRequest localData;

    public List<Restaurant> toRestaurants() {
        return localData.toRestaurants();
    }

    public int getTotalCount() {
        return localData.getTotalCount();
    }
}
