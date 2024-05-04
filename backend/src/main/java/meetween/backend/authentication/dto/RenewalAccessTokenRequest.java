package meetween.backend.authentication.dto;

public class RenewalAccessTokenRequest {
    private String refreshToken;

    private RenewalAccessTokenRequest() {}

    public RenewalAccessTokenRequest(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
