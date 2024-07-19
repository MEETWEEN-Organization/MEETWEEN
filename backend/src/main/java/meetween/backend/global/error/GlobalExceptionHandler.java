package meetween.backend.global.error;

import meetween.backend.appointment.exception.InvalidAppointmentException;
import meetween.backend.appointment.exception.NoExistAppointmentException;
import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.appointment.exception.NotAdminMemberException;
import meetween.backend.authentication.exception.InvalidOAuthServiceException;
import meetween.backend.category.exception.InvalidCategoryColorException;
import meetween.backend.category.exception.InvalidCategoryException;
import meetween.backend.location.exception.InvalidLocationTypeException;
import meetween.backend.location.exception.NoExistLocationException;
import meetween.backend.location.exception.NotOnlyOneLocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // TODO: RuntimeException 에 대한 애플리케이션 커스텀 클래스 세부 예외처리

    @ExceptionHandler({
            NoExistAppointmentException.class,
            NoExistAppointmentUserException.class,
            NoExistLocationException.class
    })
    public ResponseEntity<ExceptionResponse> handleNoSuchData(final RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler({
            InvalidAppointmentException.class,
            InvalidLocationTypeException.class,
            InvalidCategoryException.class,
            InvalidCategoryColorException.class,
            NotAdminMemberException.class,
            NotOnlyOneLocationException.class
    })
    public ResponseEntity<ExceptionResponse> handleInvalidData(final RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler({InvalidOAuthServiceException.class})
    public ResponseEntity<ExceptionResponse> handleOAuthServiceException(final RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ExceptionResponse> handleRuntimeException(final RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestBody() {
        ExceptionResponse exceptionResponse = new ExceptionResponse("잘못된 형식의 요청 Body 입니다.");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleNotSupportedMethod() {
        ExceptionResponse exceptionResponse = new ExceptionResponse("지원되지 않고있는 잘못된 HTTP 메소드 요청입니다.");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleTypeMismatch() {
        ExceptionResponse exceptionResponse = new ExceptionResponse("잘못된 데이터 형식입니다.");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public void handleNoResourceFound() {
        log.warn("정적 리소스가 존재하지 않습니다.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleUnExpectedException(final Exception exception) {
        log.warn(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse("서버에 오류가 발생했습니다.");
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }
}
