package meetween.backend.auth.service.client;

import meetween.backend.user.client.OAuthClient;
import meetween.backend.user.dto.OAuthMember;

public class StubOAuthClient implements OAuthClient {
    @Override
    public OAuthMember getOAuthMember(final String code) {
        return new OAuthMember("Fake Email", "Fake Name", "Fake Profile Image Uri");
    }
}
