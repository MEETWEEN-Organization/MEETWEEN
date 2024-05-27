package meetween.backend.location.exception;

public class NoExistLocationException extends RuntimeException {

    public NoExistLocationException(final String message) {
        super(message);
    }

    public NoExistLocationException() {
        super("장소가 존재하지 않습니다.");
    }
}
