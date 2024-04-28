package meetween.backend.member.service;

import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @DisplayName("회원을 저장한다.")
    @Test
    void 회원을_저장한다() {
        // given
        Member member = 수현_유저();

        // when
        Member actual = memberService.save(member);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("주어진 소셜 로그인 아이디로 가입된 회원이 있는지 확인한다.")
    @CsvSource(value = {"already-registered@naver.com,true", "not-registered@naver.com,false"})
    @ParameterizedTest
    void 주어진_소셜_로그인_아이디로_가입된_회원이_있는지_확인한다(String input, boolean expected) {
        // given
        String socialLoginId = "already-registered@naver.com";
        String profileImageUrl = "https://avatars.githubusercontent.com/u/88240193?v=4";
        String displayName = "이민성";
        Member member = new Member(socialLoginId, profileImageUrl, displayName, SocialType.KAKAO);
        memberService.save(member);

        // when
        boolean actual = memberService.existsBySocialLoginId(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
