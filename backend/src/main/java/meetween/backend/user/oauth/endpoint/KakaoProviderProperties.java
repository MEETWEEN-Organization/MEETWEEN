package meetween.backend.user.oauth.endpoint;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoProviderProperties implements ProviderProperties {
    private static final String KAKAO_PROPERTIES_PATH = "https://kauth.kakao.com/oauth/authorize";
    private final String clientId; // 카카오 개발자센터에서 발급 받은 Rest API Key
    private final String redirectUri; // 카카오 개발자센터에서 설정한 Redirect URI
    private final String response_type = "code"; // 클라이언트 페이지에서 얻은 인가 코드
    private final List<String> scopes = List.of("profile_nickname", "profile_image", "account_email", "birthday", "gender");
   //  private final String clientSecret;


    public KakaoProviderProperties(@Value("${oauth.properties.kakao.redirect_uri}") final String redirectUri,
                         @Value("${oauth.properties.kakao.client_id}") final String clientId) {
        this.redirectUri = redirectUri;
        this.clientId = clientId;
    }

    // generate() 를 통해 Resource owner 에게 리다이렉트 URL을 전달한다.
    @Override
    public String generate() {
        return KAKAO_PROPERTIES_PATH + "?"
                + "client_id=" + clientId + "&"
                + "redirect_uri=" + redirectUri + "&"
                + "response_type=code&"
                + "scope=" + generateScope();
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    private String generateScope() {
        String newScope = "";
        for(String scope : scopes) {
            newScope += scope;
        }
        return newScope;
    }
}
