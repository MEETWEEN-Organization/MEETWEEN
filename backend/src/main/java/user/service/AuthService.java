package user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final String KAKAO_OAUTH_END_POINT = "https://kauth.kakao.com/oauth/authorize";
    private final String kakaoRedirectUri;
    private final String kakaoClientId;
    private final String kakaoClientSecret;

    public AuthService(@Value("${oauth.properties.kakao.redirect_uri}") final String kakaoRedirectUri,
                       @Value("${oauth.properties.kakao.client_id}") final String kakaoClientId,
                       @Value("${oauth.properties.kakao.client_secret}") final String kakaoClientSecret) {
        this.kakaoRedirectUri = kakaoRedirectUri;
        this.kakaoClientId = kakaoClientId;
        this.kakaoClientSecret = kakaoClientSecret;
    }

    public String getKakaoLink() {
        return KAKAO_OAUTH_END_POINT + "?"
                + "client_id=" + kakaoClientId + "&"
                + "redirect_uri=" + kakaoRedirectUri + "&"
                + "response_type=code&"
                + "scope=account_email,gender";
    }
    // https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code&scope=account_email,gender
}
