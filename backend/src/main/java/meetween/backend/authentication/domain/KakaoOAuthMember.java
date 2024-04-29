package meetween.backend.authentication.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoOAuthMember implements OAuthMember {
    @JsonProperty("id")
    private String socialLoginId;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    private class KakaoAccount {
        @JsonProperty("profile")
        private KakaoProfile kakaoProfile;
    }

    private class KakaoProfile {
        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("profile_image_url")
        private String image;
    }

    public KakaoOAuthMember(String socialLoginId, String nickname, String imageurl) {
        this.socialLoginId = socialLoginId;
        this.kakaoAccount = new KakaoAccount();
        this.kakaoAccount.kakaoProfile = new KakaoProfile();
        this.kakaoAccount.kakaoProfile.nickname = nickname;
        this.kakaoAccount.kakaoProfile.image = imageurl;
    }


    @Override
    public String getSocialLoginId() {
        return socialLoginId;
    }

    @Override
    public String getNickname() {
        return kakaoAccount.kakaoProfile.nickname;
    }

    @Override
    public String getImageUrl() {
        return kakaoAccount.kakaoProfile.image;
    }
}
