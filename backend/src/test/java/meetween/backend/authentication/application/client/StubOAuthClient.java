package meetween.backend.authentication.application.client;

import meetween.backend.authentication.domain.client.OAuthClient;
import meetween.backend.authentication.domain.oauthmember.KakaoOAuthMember;
import org.springframework.stereotype.Component;

@Component
public class StubOAuthClient implements OAuthClient {
    @Override
    public KakaoOAuthMember getOAuthMember(final String code) {
        return new KakaoOAuthMember("fake_social_id", "fake_name", "fake_img_url");
    }

    @Override
    public boolean isSame(String name) {
        return true;
    }
}