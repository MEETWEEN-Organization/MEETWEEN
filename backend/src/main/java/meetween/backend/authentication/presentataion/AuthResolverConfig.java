package meetween.backend.authentication.presentataion;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthResolverConfig implements WebMvcConfigurer {
    private final AuthArgumentResolver authArgumentResolver;

    public AuthResolverConfig(final AuthArgumentResolver authPrincipalArgumentResolver) {
        this.authArgumentResolver = authPrincipalArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authArgumentResolver);
    }
}
