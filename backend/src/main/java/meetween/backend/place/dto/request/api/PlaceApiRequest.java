package meetween.backend.place.dto.request.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.coordinate.CoordinateConverter;
import meetween.backend.place.domain.coordinate.SpecificCoordinate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceApiRequest {

    private static final String IS_CLOSED = "폐업";

    @JsonProperty("MGTNO")
    private String id;

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

    public PlaceApiRequest(String id, String name, String address, String type, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        SpecificCoordinate specificCoordinate = CoordinateConverter.convertToWGS84(latitude, longitude);
        this.latitude = specificCoordinate.getLatitude();
        this.longitude = specificCoordinate.getLongitude();
        System.out.println(latitude);
        System.out.println(longitude);
    }

    public <T> Optional<T> toPlace(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (latitude == null || longitude == null || Objects.equals(status, IS_CLOSED)) {
            return Optional.empty();
        }
        SpecificCoordinate specificCoordinate = CoordinateConverter.convertToWGS84(latitude, longitude);
        Constructor<T> constructor = clazz.getConstructor(String.class, String.class, String.class, String.class, BigDecimal.class, BigDecimal.class);
        return Optional.of(constructor.newInstance(id, name, address, type, specificCoordinate.getLatitude(), specificCoordinate.getLongitude()));
    }
}
