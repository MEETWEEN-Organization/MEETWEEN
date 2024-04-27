package meetween.backend.support.fixture.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import meetween.backend.authentication.dto.TokenRequest;
import meetween.backend.authentication.dto.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MemberAcceptanceFixture {
    public static ExtractableResponse<Response> 자신의_정보를_조회한다(final TokenResponse tokenResponse) {
        return RestAssured.given().log().all()
                .auth().oauth2(tokenResponse.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/user/about")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    public static TokenResponse 자체_토큰을_생성하고_리턴한다(final String oauthProvider, final String authorizationCode) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new TokenRequest(authorizationCode))
                .when().post("/auth/{provider}/token", oauthProvider)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(TokenResponse.class);
    }
}
