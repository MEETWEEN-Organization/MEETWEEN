package meetween.backend.place.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RestaurantsByLocationRequest {

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal latitude;

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal longitude;

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal latitudeDelta;

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal longitudeDelta;

    private RestaurantsByLocationRequest() {}

    public RestaurantsByLocationRequest(final BigDecimal latitude, final BigDecimal longitude, final BigDecimal latitudeDelta, final BigDecimal longitudeDelta) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.latitudeDelta = latitudeDelta;
        this.longitudeDelta = longitudeDelta;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }


    public BigDecimal getLatitudeDelta() {
        return latitudeDelta;
    }

    public BigDecimal getLongitudeDelta() {
        return longitudeDelta;
    }
}
