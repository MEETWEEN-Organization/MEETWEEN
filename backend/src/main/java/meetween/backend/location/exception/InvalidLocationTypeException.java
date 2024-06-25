package meetween.backend.location.exception;

public class InvalidLocationTypeException extends RuntimeException {
    public InvalidLocationTypeException(final String message) {
        super(message);
    }

    public InvalidLocationTypeException() {
        super("잘못된 장소 타입입니다.");
    }
}
