package meetween.backend.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.CoordinateConverter;
import meetween.backend.place.domain.Restaurant;
import meetween.backend.place.domain.SpecificCoordinate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceApiRequest {

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


    private PlaceApiRequest() {}

    public PlaceApiRequest(String restaurantId, String name, String address, String type, BigDecimal latitude, BigDecimal longitude) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.type = type;
        SpecificCoordinate specificCoordinate = CoordinateConverter.convertToWGS84(latitude, longitude);
        this.latitude = specificCoordinate.getLatitude();
        this.longitude = specificCoordinate.getLongitude();
        System.out.println(latitude);
        System.out.println(longitude);
    }

    public Optional<Restaurant> toRestaurant() {
        if (latitude == null || longitude == null || Objects.equals(status, IS_CLOSED)) {
            return Optional.empty();
        }
        SpecificCoordinate specificCoordinate = CoordinateConverter.convertToWGS84(latitude, longitude);
        return Optional.of(new Restaurant(restaurantId, name, address, type, specificCoordinate.getLatitude(), specificCoordinate.getLongitude()));
    }
}
