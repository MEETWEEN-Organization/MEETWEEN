package meetween.backend.authentication.domain;

public interface OAuthMember {
    String getSocialLoginId();
    String getNickname();
    String getImageUrl();
}
