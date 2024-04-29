package meetween.backend.authentication.service.client;

import meetween.backend.authentication.domain.client.OAuthClient;
import meetween.backend.authentication.domain.oauthmember.KakaoOAuthMember;

public class StubOAuthClient implements OAuthClient {
    @Override
    public KakaoOAuthMember getOAuthMember(final String code) {
        return new KakaoOAuthMember("fake_social_id", "fake_name", "fake_img_url");
    }
}