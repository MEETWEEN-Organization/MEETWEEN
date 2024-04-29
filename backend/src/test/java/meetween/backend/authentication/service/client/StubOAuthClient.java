package meetween.backend.authentication.service.client;

import meetween.backend.authentication.domain.OAuthMember;
import meetween.backend.authentication.infrastructure.client.OAuthClient;
import meetween.backend.authentication.domain.KakaoOAuthMember;

public class StubOAuthClient implements OAuthClient {
    @Override
    public KakaoOAuthMember getOAuthMember(final String code) {
        return new KakaoOAuthMember("fake_social_id", "fake_name", "fake_img_url");
    }
}