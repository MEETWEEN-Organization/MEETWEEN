package meetween.backend.authentication.infrastructure;

import meetween.backend.authentication.infrastructure.provider.JwtTokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JwtTokenProviderTest {
    private static final String JWT_SECRET_KEY = "A".repeat(32);
    private static final int JWT_EXPIRE_LENGTH = 3600;

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, JWT_EXPIRE_LENGTH);

    @DisplayName("JWT 토큰을 생성한다.")
    @Test
    void JWT_토큰을_생성한다() {
        // given
        String payload = "hello";

        // when
        String actual = jwtTokenProvider.createToken(payload);

        // then
        Assertions.assertThat(actual.split("\\.")).hasSize(3);
    }
}
