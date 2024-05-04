package meetween.backend.authentication.domain.token;

public class MemberToken {
    private final String accessToken;
    private final String refreshToken;

    public MemberToken(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
