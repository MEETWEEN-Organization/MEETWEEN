package meetween.backend.global.config.ratelimit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiter rateLimiter;
    private static final Logger logger = LoggerFactory.getLogger(RateLimitInterceptor.class);


    public RateLimitInterceptor(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (rateLimiter.tryConsume(1)) {
            return true;
        }
        response.sendError(429, "요청 횟수가 너무 많습니다. 잠시후에 시도해주세요.");
        return false;
    }
}
