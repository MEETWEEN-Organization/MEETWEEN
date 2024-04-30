package meetween.backend.authentication.domain.client;

import meetween.backend.authentication.domain.oauthmember.KakaoOAuthMember;

public interface OAuthClient {
    KakaoOAuthMember getOAuthMember(final String code);
    boolean isSame(final String name);
}
