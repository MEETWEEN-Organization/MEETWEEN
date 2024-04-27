package meetween.backend.support.fixture.common;

import meetween.backend.member.domain.SocialType;
import meetween.backend.member.domain.Member;

public class UserFixtures {
    // 수현
    public static final String 수현_아이디 = "soozzang";
    public static final String 수현_프로필_이미지 = "https://avatars.githubusercontent.com/u/88240193?v=4";
    public static final String 수현_이름 = "수현";
    public static Member 수현_유저_생성() {
        return new Member(
                "soozzang",
                "https://avatars.githubusercontent.com/u/88240193?v=4",
                "수현",
                SocialType.KAKAO
        );
    }
}