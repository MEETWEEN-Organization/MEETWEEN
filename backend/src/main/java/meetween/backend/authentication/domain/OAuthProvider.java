package meetween.backend.authentication.domain;

import java.util.List;
import meetween.backend.authentication.domain.client.OAuthClient;
import meetween.backend.authentication.exception.InvalidOAuthServiceException;
import meetween.backend.authentication.infrastructure.uri.OAuthUriProvider;
import org.springframework.stereotype.Component;

@Component
public class OAuthProvider {
    private final List<OAuthClient> oAuthClients;
    private final List<OAuthUriProvider> oAuthUriProviders;

    public OAuthProvider(final List<OAuthClient> oAuthClients, final List<OAuthUriProvider> oAuthUriProviders) {
        this.oAuthClients = oAuthClients;
        this.oAuthUriProviders = oAuthUriProviders;
    }

    public OAuthClient getOauthClient(final String provider) {
        return oAuthClients.stream()
                .filter(oAuthClient -> oAuthClient.isSame(provider))
                .findFirst()
                .orElseThrow(InvalidOAuthServiceException::new);
    }

    public OAuthUriProvider getOAuthUriProvider(final String provider) {
        return oAuthUriProviders.stream()
                .filter(oAuthUriProvider -> oAuthUriProvider.isSame(provider))
                .findFirst()
                .orElseThrow(InvalidOAuthServiceException::new);
    }
}
