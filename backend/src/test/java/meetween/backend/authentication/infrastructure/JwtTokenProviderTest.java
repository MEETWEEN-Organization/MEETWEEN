package meetween.backend.authentication.infrastructure;

import static meetween.backend.support.fixture.common.AuthenticationFixtures.JWT_SECRET_KEY;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.JWT_EXPIRE_LENGTH;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.jwt_payload;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import meetween.backend.authentication.exception.InvalidTokenException;
import meetween.backend.authentication.infrastructure.provider.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JwtTokenProviderTest {

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, JWT_EXPIRE_LENGTH);

    @DisplayName("JWT 토큰을 생성한다.")
    @Test
    void JWT_토큰을_생성한다() {
        // given, when
        String actual = jwtTokenProvider.createToken(jwt_payload);

        // then
        assertThat(actual.split("\\.")).hasSize(3);
    }

    @DisplayName("JWT 토큰의 Payload를 가져온다.")
    @Test
    void JWT_토큰의_Payload를_가져온다() {
        // given
        String expected = jwt_payload;
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
        String expiredToken = expiredJwtTokenProvider.createToken(jwt_payload);

        // when, then
        assertThatThrownBy(() -> jwtTokenProvider.validateToken(expiredToken))
                .isInstanceOf(InvalidTokenException.class);
    }

    @DisplayName("validationToken 메소드는 유효하지 않은 토큰을 전달하면 예외를 던진다.")
    @Test
    void validateToken_메소드는_유효하지_않은_토큰을_전달하면_예외를_발생시킨다() {
        // given
        String invalidToken = "invalid-token";

        // when, then
        assertThatThrownBy(() -> jwtTokenProvider.validateToken(invalidToken))
                .isInstanceOf(InvalidTokenException.class);
    }
}
