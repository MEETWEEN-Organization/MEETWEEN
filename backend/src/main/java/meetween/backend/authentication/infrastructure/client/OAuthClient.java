package meetween.backend.authentication.infrastructure.client;

import meetween.backend.authentication.dto.OAuthMember;

@FunctionalInterface
public interface OAuthClient {
    OAuthMember getOAuthMember(final String code);
}
