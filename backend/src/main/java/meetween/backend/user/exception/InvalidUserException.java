package meetween.backend.user.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
        super("잘못된 회원의 정보입니다.");
    }

    public InvalidUserException(final String message) {
        super(message);
    }
}
