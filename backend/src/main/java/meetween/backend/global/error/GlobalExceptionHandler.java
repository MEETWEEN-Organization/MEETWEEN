package meetween.backend.global.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ControllerAdvice.class);
    public void handleDefaultWException() {}

    // TODO: 애플리케이션 실행시 발생하는 모든 예외 처리 Handler 구현
}
