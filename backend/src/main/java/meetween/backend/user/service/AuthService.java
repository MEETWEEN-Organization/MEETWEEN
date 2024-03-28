package meetween.backend.user.service;

import meetween.backend.user.client.OAuthClient;
import meetween.backend.user.dto.OAuthMember;
import meetween.backend.user.dto.TokenResponse;
import meetween.backend.user.oauth.endpoint.ProviderProperties;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final ProviderProperties providerProperties;
    private final OAuthClient oAuthClient;

    public AuthService(final ProviderProperties providerProperties, OAuthClient oAuthClient) {
        this.providerProperties = providerProperties;
        this.oAuthClient = oAuthClient;
    }

    public String getSocialLink() {
        return providerProperties.generate();
    }

    public TokenResponse getTokenWithCode(final String code) {
        OAuthMember oAuthMember = oAuthClient.getOAuthMember(code);
        return null;
    }
}
