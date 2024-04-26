package meetween.backend.support.fixture.common;

public class AuthenticationFixtures {
    public static final String kakaoOAuthProvider = "kakao";
    public static final String authorizationCode = "authorization code";

    // jwt
    public static final String JWT_SECRET_KEY = "A".repeat(32);
    public static final int JWT_EXPIRE_LENGTH = 3600;
    public static final String JWT_PAYLOAD = "payload";

    // stub oauth client
    public static final String fake_social_id = "fake_social_id";
}
