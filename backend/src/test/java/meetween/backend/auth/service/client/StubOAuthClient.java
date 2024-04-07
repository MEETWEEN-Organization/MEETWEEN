package meetween.backend.auth.service.client;

import meetween.backend.authentication.client.OAuthClient;
import meetween.backend.authentication.dto.OAuthMember;

public class StubOAuthClient implements OAuthClient {
    @Override
    public OAuthMember getOAuthMember(final String code) {
        return new OAuthMember("Fake Social Login Id", "Fake Name", "Fake Profile Image Uri");
    }
}
