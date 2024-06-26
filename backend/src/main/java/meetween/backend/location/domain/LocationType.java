package meetween.backend.location.domain;

import meetween.backend.location.exception.InvalidLocationTypeException;

import java.util.Objects;

public enum LocationType {
    CHOICED("choiced"),
    PROPOSED("proposed");

    private final String name;

    private LocationType(String name) {
        this.name = name;
    }

    public static LocationType getLocationType(String locationType) {
        if (Objects.equals(locationType, CHOICED.name)) {
            return CHOICED;
        }
        if (Objects.equals(locationType, PROPOSED.name)) {
            return PROPOSED;
        }
        throw new InvalidLocationTypeException();
    }

    public static LocationType changeLocationType(String locationType) {
        if (locationType.equals(CHOICED.name)) {
            return PROPOSED;
        } else if (locationType.equals(PROPOSED.name)) {
            return CHOICED;
        }
        throw new InvalidLocationTypeException();
    }
}
