package meetween.backend.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.Restaurant;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantApiRequest {

    @JsonProperty("BPLCNM")
    private String name;

    @JsonProperty("RDNWHLADDR")
    private String address;

    @JsonProperty("UPTAENM")
    private String type;

    @JsonProperty("Y")
    private BigDecimal latitude;

    @JsonProperty("X")
    private BigDecimal longitude;


    private RestaurantApiRequest() {}

    public RestaurantApiRequest(String name, String address, String type, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Restaurant toRestaurant() {
        return new Restaurant(name, address, type, latitude, longitude);
    }
}
