package meetween.backend.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.Restaurant;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantApiRequest {

    private static final String IS_CLOSED = "폐업";

    @JsonProperty("MGTNO")
    private String restaurantId;

    @JsonProperty("BPLCNM")
    private String name;

    @JsonProperty("TRDSTATENM")
    private String status;

    @JsonProperty("RDNWHLADDR")
    private String address;

    @JsonProperty("UPTAENM")
    private String type;

    @JsonProperty("Y")
    private BigDecimal latitude;

    @JsonProperty("X")
    private BigDecimal longitude;


    private RestaurantApiRequest() {}

    public RestaurantApiRequest(String restaurantId, String name, String address, String type, BigDecimal latitude, BigDecimal longitude) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Optional<Restaurant> toRestaurant() {
        if (latitude == null || longitude == null || Objects.equals(status, IS_CLOSED)) {
            return Optional.empty();
        }
        return Optional.of(new Restaurant(restaurantId, name, address, type, latitude, longitude));
    }
}
