package meetween.backend.support.fixture.common;

import meetween.backend.authentication.domain.token.MemberToken;
import meetween.backend.authentication.dto.RenewalAccessTokenRequest;
import meetween.backend.authentication.dto.RenewalAccessTokenResponse;
import meetween.backend.authentication.dto.TokenRequest;
import meetween.backend.authentication.dto.TokenResponse;

public class AuthenticationFixtures {
    public static final String KAKAO_OAUTH_PROVIDER = "kakao";
    public static final String AUTHORIZATION_CODE = "authorization code";

    // jwt
    public static final String JWT_SECRET_KEY = "A".repeat(32);
    public static final int EXPIRED_TOKEN_LENGTH = 0;
    public static final int ACCESS_TOKEN_EXPIRE_LEGNTH = 3600;
    public static final int REFRESH_TOKEN_EXPIRE_LENGTH = 3600 * 7;

    public static final String JWT_PAYLOAD = "payload";

    // stub oauth client
    public static final String FAKE_SOCIAL_ID = "fake_social_id";
    public static final String FAKE_NAME = "fake_name";
    public static final String FAKE_IMG_URL = "fake_img_url";

    public static MemberToken 토큰_응답() {
        return new MemberToken("access-token", "refresh-token");
    }

    public static TokenRequest 토큰_생성_요청() {
        return new TokenRequest("authorization-code");
    }

    public static RenewalAccessTokenRequest 토큰_갱신_요청() {
        return new RenewalAccessTokenRequest("refresh-token");
    }

    public static RenewalAccessTokenResponse 토큰_갱신_응답() {
        return new RenewalAccessTokenResponse("access-token");
    }
}
