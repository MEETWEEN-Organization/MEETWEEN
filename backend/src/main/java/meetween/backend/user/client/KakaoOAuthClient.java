package meetween.backend.user.client;

import meetween.backend.user.dto.OAuthMember;
import org.springframework.stereotype.Component;

@Component
public class KakaoOAuthClient implements OAuthClient {
    @Override
    public OAuthMember getOAuthMember(String code) {
        return null;
    }
}
