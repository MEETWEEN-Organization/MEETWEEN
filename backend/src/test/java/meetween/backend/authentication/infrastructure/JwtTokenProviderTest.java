package meetween.backend.authentication.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import meetween.backend.authentication.exception.InvalidTokenException;
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

    @DisplayName("validateToken 메소드는 만료된 토큰을 전달받으면 예외를 발생시킨다.")
    @Test
    void validateToken_메소드는_만료된_토큰을_전달받으면_예외를_발생시킨다() {
        // given
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, 0);
        String expiredTokeen = expiredJwtTokenProvider.createToken("payload");

        // when, then
        assertThatThrownBy(() -> jwtTokenProvider.validateToken(expiredTokeen))
                .isInstanceOf(InvalidTokenException.class);
    }

    @DisplayName("validationToken 메소드는 유효하지 않은 토큰을 전달하면 예외를 던진다.")
    @Test
    void validateToken_메소드는_유효하지_않은_토큰을_전달하면_예외를_발생시킨다() {
        // given
        String invalidToken = "hello-meetween";

        // when, then
        assertThatThrownBy(() -> jwtTokenProvider.validateToken(invalidToken))
                .isInstanceOf(InvalidTokenException.class);
    }
}
