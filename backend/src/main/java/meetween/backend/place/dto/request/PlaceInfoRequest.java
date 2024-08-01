package meetween.backend.place.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.Cafe;
import meetween.backend.place.domain.Restaurant;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceInfoRequest {

    @JsonProperty("list_total_count")
    private int totalCount;
    @JsonProperty("row")
    private List<PlaceApiRequest> items;

    public List<Restaurant> toRestaurants() {
        return items.stream()
                .map(PlaceApiRequest::toRestaurant)
                .flatMap(Optional::stream)
                .toList();
    }

    public List<Cafe> toCafes() {
        return items.stream()
                .map(PlaceApiRequest::toCafe)
                .flatMap(Optional::stream)
                .toList();
    }

    public int getTotalCount() {
        return totalCount;
    }
}
