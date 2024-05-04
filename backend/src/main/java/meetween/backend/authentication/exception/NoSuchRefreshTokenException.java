package meetween.backend.authentication.exception;

public class NoSuchRefreshTokenException extends RuntimeException {

    public NoSuchRefreshTokenException() {
        super("토큰이 존재하지 않습니다.");
    }

    public NoSuchRefreshTokenException(final String message) {
        super(message);
    }
}
