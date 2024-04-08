package meetween.backend.authentication.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("유효하지 않은 요청입니다.");
    }

    public BadRequestException(final String message) {
        super(message);
    }
}

