package meetween.backend.authentication.domain.oauthmember;

public interface OAuthMember {
    String getSocialLoginId();
    String getNickname();
    String getImageUrl();
}
