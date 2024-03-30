package meetween.backend.config;

import meetween.backend.auth.service.client.StubOAuthClient;
import meetween.backend.user.client.OAuthClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public OAuthClient oAuthClient() {
        return new StubOAuthClient();
    }
}
