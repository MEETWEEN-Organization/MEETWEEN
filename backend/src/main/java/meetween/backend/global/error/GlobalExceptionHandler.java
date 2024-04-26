package meetween.backend.global.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(final RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleUnExpectedException(final Exception exception) {
        log.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse("서버에 오류가 발생했습니다.");
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }
}
