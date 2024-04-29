package meetween.backend.authentication.domain.client;

import java.util.List;
import meetween.backend.authentication.exception.InvalidOAuthServiceException;
import meetween.backend.authentication.domain.client.OAuthClient;
import org.springframework.stereotype.Component;

@Component
public class OAuthClientProvider {
    private final List<OAuthClient> oAuthClients;

    public OAuthClientProvider(final List<OAuthClient> oAuthClients) {
        this.oAuthClients = oAuthClients;
    }

    public OAuthClient getOauthClient(final String provider) {
        return oAuthClients.stream()
                .filter(oAuthClient -> oAuthClient.isSame(provider))
                .findFirst()
                .orElseThrow(InvalidOAuthServiceException::new);
    }
}
