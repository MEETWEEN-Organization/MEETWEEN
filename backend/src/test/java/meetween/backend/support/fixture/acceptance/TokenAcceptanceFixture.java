package meetween.backend.support.fixture.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import meetween.backend.authentication.dto.RenewalAccessTokenRequest;
import meetween.backend.authentication.dto.TokenRequest;
import meetween.backend.authentication.dto.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class TokenAcceptanceFixture {
    public static ExtractableResponse<Response> 자체_토큰을_생성한다(final String oauthProvider, final String code) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new TokenRequest(code))
                .when().post("/auth/{provider}/token", oauthProvider)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    public static ExtractableResponse<Response> OAuth_인증_URI를_생성한다(final String oauthProvider) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/auth/{provider}/link", oauthProvider)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }


    public static String 자체_토큰을_생성하고_엑세스_토큰을_리턴한다(final String oauthProvider, final String code) {
        TokenResponse accessAndRefreshTokenResponse = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new TokenRequest(code))
                .when().post("/auth/{provider}/token", oauthProvider)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(TokenResponse.class);

        return accessAndRefreshTokenResponse.getAccessToken();
    }

    public static ExtractableResponse<Response> 리프레시_토큰을_통해_새로운_엑세스_토큰을_재발급_한다(
            final RenewalAccessTokenRequest renewalAccessTokenRequest) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(renewalAccessTokenRequest)
                .when().post("/auth/token/renewal")
                .then().log().all()
                .extract();
    }
}
