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
    private final BearerTokenExtractor bearerTokenExtractor;
    private static final String BEARER_TYPE = "Bearer ";

    public AuthArgumentResolver(final JwtTokenProvider jwtTokenProvider, final BearerTokenExtractor bearerTokenExtractor) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.bearerTokenExtractor = bearerTokenExtractor;
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

        final String accessToken = extractValidAccessToken(request);
        final Long memberId = Long.valueOf(jwtTokenProvider.getMemberId(accessToken));

        return new LoginMember(memberId);
    }

    public String extractValidAccessToken(final HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        validateEmptyHeader(authorizationHeader);
        validateAuthorizationFormat(authorizationHeader);

        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }

    private void validateAuthorizationFormat(final String authorizationHeader) {
        if(!authorizationHeader.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
            throw new InvalidTokenException();
        }
    }

    private void validateEmptyHeader(String authorizationHeader) {
        if(Objects.isNull(authorizationHeader)) {
            throw new EmptyAuthHeaderException();
        }
    }
}