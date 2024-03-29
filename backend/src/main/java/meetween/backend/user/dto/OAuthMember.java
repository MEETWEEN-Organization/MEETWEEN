package meetween.backend.user.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthMember {
    @JsonProperty("id")
    private String socialLoginId;

    @JsonProperty("kakao_account.profile.nickname")
    private String nickname;
    @JsonProperty("kakao_account.profile.profile_image_url")
    private String image;

    public OAuthMember(String socialLoginId, String nickname, String image) {
        this.socialLoginId = socialLoginId;
        this.nickname = nickname;
        this.image = image;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getImageUrl() {
        return image;
    }
}
