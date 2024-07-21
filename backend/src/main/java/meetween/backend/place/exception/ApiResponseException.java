package meetween.backend.place.exception;

public class ApiResponseException extends RuntimeException {
    public ApiResponseException(final String message) {
        super(message);
    }

    public ApiResponseException() {
        super("API를 기다리는 동안 문제가 발생하였습니다.");
    }
}
