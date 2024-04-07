package meetween.backend.authentication.client;

import meetween.backend.authentication.dto.OAuthMember;

@FunctionalInterface
public interface OAuthClient {
    OAuthMember getOAuthMember(final String code);
}
