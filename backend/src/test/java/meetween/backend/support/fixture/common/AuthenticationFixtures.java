package meetween.backend.support.fixture.common;

public class AuthenticationFixtures {
    public static final String KAKAO_OAUTH_PROVIDER = "kakao";
    public static final String AUTHORIZATION_CODE = "authorization code";

    // jwt
    public static final String JWT_SECRET_KEY = "A".repeat(32);
    public static final int JWT_EXPIRE_LENGTH = 3600;
    public static final String JWT_PAYLOAD = "payload";

    // stub oauth client
    public static final String FAKE_SOCIAL_ID = "fake_social_id";
    public static final String FAKE_NAME = "fake_name";
    public static final String FAKE_IMG_URL = "fake_img_url";
}
