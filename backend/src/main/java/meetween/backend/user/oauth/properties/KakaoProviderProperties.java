package meetween.backend.user.oauth.properties;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


// Resource owner 에게 리다이렉트 URL을 전달한다. (카카오 로그인 시도 창 URI 를 만드는 역할)
@Component
public class KakaoProviderProperties implements OAuthProviderProperties {
    private static final String KAKAO_PROPERTIES_PATH = "https://kauth.kakao.com/oauth/authorize";
    private final String clientId; // 카카오 개발자센터에서 발급받은 JavaScript Key
    private final String redirectUri; // 카카오 개발자센터에서 설정한 Reedirect URI
    private final String response_type = "code"; //  'code'로 고정
    private final List<String> scopes = List.of("profile_nickname", "profile_image", "account_email", "birthday", "gender"); // 카카오 개발자센터에서 설정한 동의 항목
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

    private String generateScope() {
        String newScope = "";
        for(String scope : scopes) {
            newScope += scope;
            newScope += " ";
        }
        return newScope;
    }
}
