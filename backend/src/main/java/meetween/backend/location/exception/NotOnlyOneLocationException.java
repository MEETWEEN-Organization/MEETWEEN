package meetween.backend.location.exception;

public class NotOnlyOneLocationException extends RuntimeException {

    public NotOnlyOneLocationException(final String message) {
        super(message);
    }

    public NotOnlyOneLocationException() {
        super("한 약속에 선택된 장소는 하나여야 합니다.");
    }
}
