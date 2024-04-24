package meetween.backend.authentication.service.client;

import meetween.backend.authentication.infrastructure.client.OAuthClient;
import meetween.backend.authentication.dto.OAuthMember;

public class StubOAuthClient implements OAuthClient {
    @Override
    public OAuthMember getOAuthMember(final String code) {
        return new OAuthMember("fake_social_id", "fake_name", "fake_img_url");
    }
}
