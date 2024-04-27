package meetween.backend.support.fixture;

import meetween.backend.user.domain.SocialType;
import meetween.backend.user.domain.User;

public class UserFixtures {

    public static final String 수현_유저_소셜_아이디 = "soozzang";
    public static final String 수현_유저_프로필_이미지 = "https://avatars.githubusercontent.com/u/88240193?v=4";
    public static final String 수현_유저_이름 = "수현";

    public static User 수현_유저() {
        return new User(
                수현_유저_소셜_아이디,
                수현_유저_프로필_이미지,
                수현_유저_이름,
                SocialType.KAKAO
        );
    }
}
