package meetween.backend.global.error;

import java.util.Objects;
import meetween.backend.member.exception.InvalidMemberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({InvalidMemberException.class})
    public ResponseEntity<ExceptionResponse> handleRuntimeException(final InvalidMemberException exception) {
        log.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception,
            final HttpHeaders headers,
            final HttpStatusCode status,
            final WebRequest request
    ) {
        log.error(exception.getMessage(), exception);

        final String errorMessage = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorMessage);
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestBody() {
        ExceptionResponse errorResponse = new ExceptionResponse("잘못된 형식의 요청 Body 입니다.");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleUnExpectedException(final Exception exception) {
        log.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse("서버에 오류가 발생했습니다.");
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }
}
