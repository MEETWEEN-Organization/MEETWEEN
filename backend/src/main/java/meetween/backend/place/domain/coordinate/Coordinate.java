package meetween.backend.place.domain.coordinate;

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

    public static Coordinate of(BigDecimal latitude, BigDecimal longitude, BigDecimal latitudeDelta, BigDecimal longitudeDelta) {
        BigDecimal minLatitude = latitude.subtract(latitudeDelta);
        BigDecimal maxLatitude = latitude.add(latitudeDelta);
        BigDecimal minLongitude = longitude.subtract(longitudeDelta);
        BigDecimal maxLongitude = longitude.add(longitudeDelta);
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
