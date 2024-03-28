package meetween.backend.user.client;

import meetween.backend.user.dto.OAuthMember;

@FunctionalInterface
public interface OAuthClient {
    OAuthMember getOAuthMember(final String code);
}
