package meetween.backend.user.exception;

public class NoExistUserException extends RuntimeException {
    public NoExistUserException() {
        super("존재하지 않는 회원입니다.");
    }

    public NoExistUserException(final String message) {
        super(message);
    }
}
