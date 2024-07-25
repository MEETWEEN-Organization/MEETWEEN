package meetween.backend.place.domain;

import org.locationtech.proj4j.*;

import java.math.BigDecimal;

public class CoordinateConverter {

    private static final String WGS84_NAME = "epsg:4326";
    private static final String EPSG_2097_NAME = "epsg:2097";

    public static SpecificCoordinate convertToWGS84(BigDecimal latitude, BigDecimal longitude) {
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem WGS84 = crsFactory.createFromName(WGS84_NAME);
        CoordinateReferenceSystem EPSG_2097 = crsFactory.createFromName(EPSG_2097_NAME);

        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform hi = ctFactory.createTransform(EPSG_2097, WGS84);
        ProjCoordinate result = new ProjCoordinate();

        ProjCoordinate transform = hi.transform(new ProjCoordinate(latitude.doubleValue(), longitude.doubleValue()), result);
        return new SpecificCoordinate(BigDecimal.valueOf(transform.y), BigDecimal.valueOf(transform.x));
    }
}
