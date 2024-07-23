package meetween.backend.place.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RestaurantsByLocationRequest {

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal latitude;

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal longitude;

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal delta;

    private RestaurantsByLocationRequest() {}

    public RestaurantsByLocationRequest(final BigDecimal latitude, final BigDecimal longitude, final BigDecimal delta) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.delta = delta;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getDelta() {
        return delta;
    }
}
