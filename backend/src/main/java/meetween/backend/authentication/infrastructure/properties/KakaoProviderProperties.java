package meetween.backend.authentication.infrastructure.properties;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


// Resource owner 에게 리다이렉트 URL을 전달한다. (카카오 로그인 시도 창 URI 를 만드는 역할)
@Component
public class KakaoProviderProperties implements OAuthProviderProperties {
    private final String authorizationUri;
    private final String clientId;
    private final String redirectUri; // 인가 코드(authorization code) 를 전달받을 uri
    // TODO: 인가 코드를 발급받는 주체는 프론트엔드 이므로, 향후 프폰트엔드 URI 로 redirectUri 를 변경
    private final String response_type = "code"; //  'code'로 고정
    private final List<String> scopes = List.of("profile_nickname", "profile_image");

    public KakaoProviderProperties(@Value("${oauth.kakao.authorize_uri}") final String authorizationUri,
                                   @Value("${oauth.kakao.client_id}") final String clientId,
                                   @Value("${oauth.kakao.redirect_uri}") final String redirectUri){
        this.authorizationUri = authorizationUri;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
    }

    // generate() 를 통해 Resource owner 에게 리다이렉트 URL을 전달한다.
    @Override
    public String generate() {
        return authorizationUri + "?"
                + "client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=" + response_type;
    }

    private String generateScope() {
        return String.join(", ", scopes);
    }

}
