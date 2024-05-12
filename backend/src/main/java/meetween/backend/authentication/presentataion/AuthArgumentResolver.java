package meetween.backend.authentication.presentataion;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import meetween.backend.authentication.dto.LoginMember;
import meetween.backend.authentication.exception.BadRequestException;
import meetween.backend.authentication.exception.EmptyAuthHeaderException;
import meetween.backend.authentication.exception.InvalidTokenException;
import meetween.backend.authentication.infrastructure.jwt.JwtTokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenProvider jwtTokenProvider;
    private static final String BEARER_TYPE = "Bearer ";

    public AuthArgumentResolver(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean supportsParameter(final MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(AuthPrincipal.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter methodParameter,
                                  final ModelAndViewContainer modelAndViewContainer,
                                   final NativeWebRequest nativeWebRequest,
                                  final WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        if (request == null) {
            throw new BadRequestException();
        }

        final String accessToken = BearerTokenExtractor.extractValidAccessToken(request);
        final Long memberId = Long.valueOf(jwtTokenProvider.getMemberId(accessToken));

        return new LoginMember(memberId);
    }
}