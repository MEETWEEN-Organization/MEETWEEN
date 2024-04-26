package meetween.backend.acceptance;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import meetween.backend.authentication.dto.TokenRequest;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.config.TestConfig;
import meetween.backend.member.dto.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@Import(TestConfig.class)
public class MemberAcceptanceTest extends AcceptenceConfig {

    @DisplayName("등록된 회원이 회원 정보를 조회하면 상태코드 200을 리턴한다.")
    @Test
    void 등록된_회원이_회원_정보를_조회하면_상태코드_200을_리턴한다() {
        // given
        String oauthProvider = "kakao";
        String authorizationCode = "member authorization code";

        TokenResponse tokenResponse = 자체_토큰을_생성하고_리턴한다(oauthProvider, authorizationCode);

        // when
        ExtractableResponse<Response> response = 자신의_정보를_조회한다(tokenResponse);
        MemberResponse userResponse = response.as(MemberResponse.class);

        // then
        assertAll(() -> {
            상태코드_200이_반환된다(response);
            assertThat(userResponse.getSocialLoginId()).isEqualTo("fake_social_id");
            assertThat(userResponse.getDisplayName()).isEqualTo("fake_name");
            assertThat(userResponse.getProfileImageUrl()).isEqualTo("fake_img_url");
        });
    }

    private TokenResponse 자체_토큰을_생성하고_리턴한다(final String oauthProvider, final String authorizationCode) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new TokenRequest(authorizationCode))
                .when().post("/auth/{provider}/token", oauthProvider)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(TokenResponse.class);
    }

    private ExtractableResponse<Response> 자신의_정보를_조회한다(final TokenResponse tokenResponse) {
        return RestAssured.given().log().all()
                .auth().oauth2(tokenResponse.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/user/about")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    private static void 상태코드_200이_반환된다(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
