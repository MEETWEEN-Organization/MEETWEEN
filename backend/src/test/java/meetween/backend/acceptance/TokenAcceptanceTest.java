package meetween.backend.acceptance;

import static meetween.backend.support.fixture.acceptance.TokenAcceptanceFixture.리프레시_토큰을_통해_새로운_엑세스_토큰을_재발급_한다;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.AUTHORIZATION_CODE;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.KAKAO_OAUTH_PROVIDER;
import static meetween.backend.support.fixture.acceptance.status.StatusFixtures.상태코드_200이_반환된다;
import static meetween.backend.support.fixture.acceptance.TokenAcceptanceFixture.자체_토큰을_생성한다;
import static meetween.backend.support.fixture.acceptance.TokenAcceptanceFixture.OAuth_인증_URI를_생성한다;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import meetween.backend.acceptance.config.AcceptenceConfig;
import meetween.backend.authentication.dto.OAuthUriResponse;
import meetween.backend.authentication.dto.RenewalAccessTokenRequest;
import meetween.backend.authentication.dto.RenewalAccessTokenResponse;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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

    @DisplayName("최초 사용자거나 기존에 존재하는 회원이 다시 로그인하는 경우 토큰들을 발급하고 상태코드 200을 반환한다.")
    @Test
    void 최초_사용자거나_기존에_존재하는_회원이_다시_로그인하는_경우_토큰들을_발급하고_상태코드_200을_반환한다() {
        // given, when
        ExtractableResponse<Response> response = 자체_토큰을_생성한다(KAKAO_OAUTH_PROVIDER, AUTHORIZATION_CODE);
        TokenResponse tokenResponse = response.as(TokenResponse.class);

        // then
        assertAll(() -> {
            상태코드_200이_반환된다(response);
            assertThat(tokenResponse.getAccessToken()).isNotEmpty();
        });
    }

    @DisplayName("리프레스 토큰을 통해 새로운 엑세스 토큰을 발급받고 200을 리턴한다")
    @Test
    void 리프레스_토큰을_통해_새로운_엑세스_토큰을_발급받고_200을_리턴한다() {
        // given
        ExtractableResponse<Response> response = 자체_토큰을_생성한다(KAKAO_OAUTH_PROVIDER, AUTHORIZATION_CODE);
        String refreshToken = response.response().getCookie("refresh-token");
        RenewalAccessTokenRequest renewalAccessTokenRequest = new RenewalAccessTokenRequest(
                refreshToken
        );

        // when
        ExtractableResponse<Response> actual = 리프레시_토큰을_통해_새로운_엑세스_토큰을_재발급_한다(renewalAccessTokenRequest);
        RenewalAccessTokenResponse renewalAccessTokenResponse = actual.as(RenewalAccessTokenResponse.class);

        // then
        assertAll(() -> {
            상태코드_200이_반환된다(response);
            assertThat(refreshToken).isNotEmpty();
        });
    }


}
