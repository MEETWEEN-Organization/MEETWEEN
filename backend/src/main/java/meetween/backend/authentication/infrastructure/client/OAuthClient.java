package meetween.backend.authentication.infrastructure.client;

import meetween.backend.authentication.domain.KakaoOAuthMember;

@FunctionalInterface
public interface OAuthClient {
    KakaoOAuthMember getOAuthMember(final String code);
}
