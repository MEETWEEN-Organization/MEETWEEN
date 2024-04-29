package meetween.backend.authentication.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OAuthMember {
    @JsonProperty("id")
    private String socialLoginId;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    private static class KakaoAccount {
        @JsonProperty("profile")
        private KakaoProfile kakaoProfile;
    }

    private static class KakaoProfile {
        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("profile_image_url")
        private String image;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getNickname() {
        return kakaoAccount.kakaoProfile.nickname;
    }

    public String getImageUrl() {
        return kakaoAccount.kakaoProfile.image;
    }
}
