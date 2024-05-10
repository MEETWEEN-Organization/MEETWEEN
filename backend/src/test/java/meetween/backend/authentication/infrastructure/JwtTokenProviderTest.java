package meetween.backend.authentication.infrastructure;

import static meetween.backend.support.fixture.common.AuthenticationFixtures.JWT_SECRET_KEY;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.EXPIRED_TOKEN_LENGTH;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.ACCESS_TOKEN_EXPIRE_LEGNTH;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.REFRESH_TOKEN_EXPIRE_LENGTH;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.JWT_PAYLOAD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import meetween.backend.authentication.domain.token.InMemoryRefreshTokenRepository;
import meetween.backend.authentication.exception.InvalidTokenException;
import meetween.backend.authentication.infrastructure.jwt.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JwtTokenProviderTest {

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, ACCESS_TOKEN_EXPIRE_LEGNTH, REFRESH_TOKEN_EXPIRE_LENGTH, new InMemoryRefreshTokenRepository());

    @DisplayName("엑세스 토큰을 생성한다.")
    @Test
    void 엑세스_토큰을_생성한다() {
        // given, when
        String actual = jwtTokenProvider.createAccessToken(1L);

        // then
        assertThat(actual.split("\\.")).hasSize(3);
    }

    @DisplayName("리프레시 토큰을 생성한다.")
    @Test
    void 리프레시_토큰을_생성한다() {
        // given, when
        String actual = jwtTokenProvider.createRefreshToken(100L);

        // then
        assertThat(actual.split("\\.")).hasSize(3);
    }

    @DisplayName("JWT 토큰의 Payload를 가져온다.")
    @Test
    void JWT_토큰의_Payload를_가져온다() {
        // given
        String expected = JWT_PAYLOAD;
        String accessToken = jwtTokenProvider.createToken(expected, ACCESS_TOKEN_EXPIRE_LEGNTH);

        // when
        String actual = jwtTokenProvider.getPayload(accessToken);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("validateToken 메소드는 만료된 토큰을 전달받으면 예외를 발생시킨다.")
    @Test
    void validateToken_메소드는_만료된_토큰을_전달받으면_예외를_발생시킨다() {
        // given
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, EXPIRED_TOKEN_LENGTH, EXPIRED_TOKEN_LENGTH, new InMemoryRefreshTokenRepository());
        String expiredToken = expiredJwtTokenProvider.createToken(JWT_PAYLOAD, EXPIRED_TOKEN_LENGTH);

        // when, then
        assertThatThrownBy(() -> jwtTokenProvider.validateToken(expiredToken))
                .isInstanceOf(InvalidTokenException.class);
    }

    @DisplayName("만료된 리프레시 토큰을 전달받으면 예외를 발생시킨다.")
    @Test
    void 만료된_리프레시_토큰을_전달받으면_예외를_발생시킨다() {
        // given
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, EXPIRED_TOKEN_LENGTH, EXPIRED_TOKEN_LENGTH, new InMemoryRefreshTokenRepository());
        String expiredToken = expiredJwtTokenProvider.createRefreshToken(1L);

        // when, then
        assertThatThrownBy(() -> jwtTokenProvider.validateToken(expiredToken))
                .isInstanceOf(InvalidTokenException.class);
    }

    @DisplayName("만료된 엑세스 토큰을 전달받으면 예외를 발생시킨다.")
    @Test
    void 만료된_엑세스_토큰을_전달받으면_예외를_발생시킨다() {
        // given
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, EXPIRED_TOKEN_LENGTH, EXPIRED_TOKEN_LENGTH, new InMemoryRefreshTokenRepository());
        String expiredToken = expiredJwtTokenProvider.createAccessToken(1L);

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
