package meetween.backend.authentication.exception;

public class InvalidOAuthServiceException extends RuntimeException {
    public InvalidOAuthServiceException() {
        super("제공되지 않는 OAuth 서비스입니다.");
    }

    public InvalidOAuthServiceException(final String message) {
        super(message);
    }
}
