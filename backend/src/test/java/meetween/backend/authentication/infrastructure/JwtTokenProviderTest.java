package meetween.backend.authentication.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import meetween.backend.authentication.infrastructure.provider.JwtTokenProvider;
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
        assertThat(actual.split("\\.")).hasSize(3);
    }

    @DisplayName("JWT 토큰의 Payload를 가져온다.")
    @Test
    void JWT_토큰의_Payload를_가져온다() {
        // given
        String expected = "Hello";
        String token = jwtTokenProvider.createToken(expected);

        // when
        String actual = jwtTokenProvider.getPayload(token);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
