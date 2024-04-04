package meetween.backend.support.fixture;

import meetween.backend.user.domain.SocialType;
import meetween.backend.user.domain.User;

public class UserFixtures {
    public static User 수현_유저_생성() {
        return new User(
                "soozzang",
                "https://avatars.githubusercontent.com/u/88240193?v=4",
                "수현",
                SocialType.KAKAO
        );
    }
}
