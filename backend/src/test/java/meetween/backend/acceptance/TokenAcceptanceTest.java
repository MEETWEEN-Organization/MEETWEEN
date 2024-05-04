package meetween.backend.acceptance;

import static meetween.backend.support.fixture.common.AuthenticationFixtures.AUTHORIZATION_CODE;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.KAKAO_OAUTH_PROVIDER;
import static meetween.backend.support.fixture.acceptance.status.StatusFixtures.상태코드_200이_반환된다;
import static meetween.backend.support.fixture.acceptance.TokenAcceptanceFixture.자체_토큰을_생성한다;
import static meetween.backend.support.fixture.acceptance.TokenAcceptanceFixture.OAuth_인증_URI를_생성한다;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import meetween.backend.acceptance.config.AcceptenceConfig;
import meetween.backend.authentication.dto.OAuthUriResponse;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

@Import(TestConfig.class)
public class TokenAcceptanceTest extends AcceptenceConfig {
    @DisplayName("카카오 authorization uri 를 생성하고 리턴한다.")
    @Test
    void 카카오_authorization_uri_를_생성하고_리턴한다() {
        // given, when
        ExtractableResponse<Response> response = OAuth_인증_URI를_생성한다(KAKAO_OAUTH_PROVIDER);
        OAuthUriResponse oAuthUriResponse = response.as(OAuthUriResponse.class);

        // then
        assertAll(() -> {
            상태코드_200이_반환된다(response);
            assertThat(oAuthUriResponse.getoAuthUri()).contains("https://");
        });
    }

    @DisplayName("최초 사용자거나 기존에 존재하는 회원인 경우 200을 발급한다.")
    @Test
    void 최초_사용자거나_기존에_존재하는_회원인_경우_200을_발급한다() {
        // given, when
        ExtractableResponse<Response> response = 자체_토큰을_생성한다(KAKAO_OAUTH_PROVIDER, AUTHORIZATION_CODE);
        TokenResponse tokenResponse = response.as(TokenResponse.class);

        // then
        assertAll(() -> {
            상태코드_200이_반환된다(response);
            assertThat(tokenResponse.getAccessToken()).isNotEmpty();
            assertThat(tokenResponse.getRefreshToken()).isNotEmpty();
        });
    }
}
