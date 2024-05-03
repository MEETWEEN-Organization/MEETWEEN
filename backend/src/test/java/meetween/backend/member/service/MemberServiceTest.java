package meetween.backend.member.service;

import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import meetween.backend.member.domain.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("회원을 저장한다.")
    @Test
    void 회원을_저장한다() {
        // given
        final Member member = 수현_유저();
        given(memberRepository.save(any(Member.class))).willReturn(member);

        // when
        final Member actual = memberService.save(member);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("주어진 소셜 로그인 아이디로 가입된 회원이 존재한다면 참을 리턴한다.")
    @Test
    void 주어진_소셜_로그인_아이디로_가입된_회원이_존재한다면_참을_리턴한다() {
        // given
        String socialLoginId = "12345678";
        String profileImageUrl = "https://avatars.githubusercontent.com/u/88240193?v=4";
        String displayName = "이민성";
        Member member = new Member(socialLoginId, profileImageUrl, displayName, SocialType.KAKAO);

        given(memberRepository.existsBySocialLoginId(socialLoginId)).willReturn(true);

        // when
        boolean actual = memberService.existsBySocialLoginId(socialLoginId);

        // then
        assertThat(actual).isTrue();
    }
}
