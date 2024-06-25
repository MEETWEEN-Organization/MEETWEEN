package meetween.backend.location.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class LocationAddRequest {

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal latitude;

    @NotNull(message = "Null일 수 없습니다.")
    private BigDecimal longitude;

    private LocationAddRequest() {}


    public LocationAddRequest(final BigDecimal latitude, final BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
