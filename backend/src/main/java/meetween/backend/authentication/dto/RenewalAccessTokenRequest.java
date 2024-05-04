package meetween.backend.authentication.dto;

public class RenewalAccessTokenRequest {
    private String accessToken;
    private String refreshToken;

    private RenewalAccessTokenRequest() {}

    public RenewalAccessTokenRequest(final String accessToken, final String refreshToken) {
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
