package meetween.backend.support.fixture;

import meetween.backend.member.domain.SocialType;
import meetween.backend.member.domain.Member;

public class UserFixtures {
    public static Member 수현_유저_생성() {
        return new Member(
                "soozzang",
                "https://avatars.githubusercontent.com/u/88240193?v=4",
                "수현",
                SocialType.KAKAO
        );
    }
}
