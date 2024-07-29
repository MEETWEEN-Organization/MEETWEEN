package meetween.backend.place.dto.response;

import meetween.backend.place.domain.Restaurant;

import java.math.BigDecimal;

public class RestaurantSpecificResponse {

    private final String id;
    private final String name;
    private final String address;
    private final String type;
    private final BigDecimal latitude;
    private final BigDecimal longitude;

    public RestaurantSpecificResponse(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAdress();
        this.type = restaurant.getType();
        this.latitude = restaurant.getLatitude();
        this.longitude = restaurant.getLongitude();
    }

    public RestaurantSpecificResponse(String id, String name, String address, String type, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
