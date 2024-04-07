package meetween.backend.authentication.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoTokenResponse {

    private String refreshToken;
    private String idToken;

    private KakaoTokenResponse() {
    }

    public KakaoTokenResponse(final String refreshToken, final String idToken) {
        this.refreshToken = refreshToken;
        this.idToken = idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getIdToken() {
        return idToken;
    }
}
