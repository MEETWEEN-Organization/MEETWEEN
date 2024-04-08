package meetween.backend.authentication.exception;

public class EmptyAuthHeaderException extends RuntimeException {
    public EmptyAuthHeaderException() {
        super("토큰이 비어있습니다.");
    }

    public EmptyAuthHeaderException(final String message) {
        super(message);
    }
}
