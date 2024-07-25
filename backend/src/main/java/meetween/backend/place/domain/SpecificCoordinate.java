package meetween.backend.place.domain;

import java.math.BigDecimal;

public class SpecificCoordinate {
    private final BigDecimal latitude;
    private final BigDecimal longitude;

    public SpecificCoordinate(BigDecimal latitude, BigDecimal longitude) {
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
