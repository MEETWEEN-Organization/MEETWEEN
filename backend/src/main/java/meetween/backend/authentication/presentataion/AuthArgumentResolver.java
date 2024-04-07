package meetween.backend.authentication.presentataion;

import jakarta.servlet.http.HttpServletRequest;
import meetween.backend.authentication.dto.LoginUser;
import meetween.backend.authentication.exception.BadRequestException;
import meetween.backend.authentication.infrastructure.provider.JwtTokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenProvider jwtTokenProvider;
    private final BearerTokenExtractor bearerTokenExtractor;

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

        String accessToken = bearerTokenExtractor.extractValidAccessToken(request);
        Long id = Long.parseLong(jwtTokenProvider.getPayload(accessToken));

        return new LoginUser(id);
    }
}