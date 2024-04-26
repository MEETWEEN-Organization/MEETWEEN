package meetween.backend.acceptance;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import meetween.backend.authentication.dto.TokenRequest;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Import(TestConfig.class)
public class TokenAcceptenceTest extends AcceptenceConfig {
    @DisplayName("최초 사용자거나 기존에 존재하는 회원인 경우 200을 발급한다.")
    @Test
    void 최초_사용자거나_기존에_존재하는_회원인_경우_200을_발급한다() {
        // given
        String oauthProvider = "kakao";
        String code = "qiwoejqioweijqowieoijjqoiwjoijuhn";

        // when
        ExtractableResponse<Response> response = 자체_토큰을_생성한다(oauthProvider, code);
        TokenResponse tokenResponse = response.as(TokenResponse.class);

        // then
        assertAll(() -> {
            상태코드_200이_반환된다(response);
            assertThat(tokenResponse.getAccessToken()).isNotEmpty();
        });
    }

    private ExtractableResponse<Response> 자체_토큰을_생성한다(final String oauthProvider, final String code) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new TokenRequest(code))
                .when().post("/auth/{provider}/token", oauthProvider)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }



    private static void 상태코드_200이_반환된다(final ExtractableResponse<io.restassured.response.Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
