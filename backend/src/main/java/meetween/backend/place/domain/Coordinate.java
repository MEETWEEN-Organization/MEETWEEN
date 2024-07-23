package meetween.backend.place.domain;

import java.math.BigDecimal;

public class Coordinate {

    private final BigDecimal minLatitude;
    private final BigDecimal maxLatitude;
    private final BigDecimal minLongitude;
    private final BigDecimal maxLongitude;

    private Coordinate(BigDecimal minLatitude, BigDecimal maxLatitude, BigDecimal minLongitude, BigDecimal maxLongitude) {
        this.minLatitude = minLatitude;
        this.maxLatitude = maxLatitude;
        this.minLongitude = minLongitude;
        this.maxLongitude = maxLongitude;
    }

    public static Coordinate of(BigDecimal latitude, BigDecimal longitude, BigDecimal delta) {
        BigDecimal minLatitude = latitude.subtract(delta);
        BigDecimal maxLatitude = latitude.add(delta);
        BigDecimal minLongitude = longitude.subtract(delta);
        BigDecimal maxLongitude = longitude.add(delta);
        return new Coordinate(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }

    public BigDecimal getMinLatitude() {
        return minLatitude;
    }

    public BigDecimal getMaxLatitude() {
        return maxLatitude;
    }

    public BigDecimal getMinLongitude() {
        return minLongitude;
    }

    public BigDecimal getMaxLongitude() {
        return maxLongitude;
    }
}
