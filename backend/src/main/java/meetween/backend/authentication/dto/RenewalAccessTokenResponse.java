package meetween.backend.authentication.dto;

public class RenewalAccessTokenResponse {
    private String accessToken;

    private RenewalAccessTokenResponse(){
    }

    public RenewalAccessTokenResponse(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
