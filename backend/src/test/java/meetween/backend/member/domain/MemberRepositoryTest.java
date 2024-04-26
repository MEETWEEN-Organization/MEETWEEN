package meetween.backend.member.domain;

import static meetween.backend.support.fixture.common.UserFixtures.수현_유저_생성;
import static meetween.backend.support.fixture.common.UserFixtures.수현_아이디;
import static org.assertj.core.api.Assertions.assertThat;

import meetween.backend.global.config.JpaAuditConfig;
import meetween.backend.support.fixture.common.UserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(JpaAuditConfig.class)
@ActiveProfiles("test")
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("중복된 로그인 아이디가 존재하는 경우 참을 리턴한다.")
    @Test
    void 중복된_이메일이_존재하는_경우_참을_리턴한다() {
        // given
        Member member = 수현_유저_생성();
        memberRepository.save(member);

        // when
        boolean actual = memberRepository.existsBySocialLoginId(수현_아이디);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("소셜 로그인 아이디를 통해 회원을 찾는다.")
    @Test
    void 소셜_로그인_아이디를_통해_회원을_찾는다() {
        // given
        Member 수현_DB = memberRepository.save(수현_유저_생성());

        // when
        Member foundMember = memberRepository.findBySocialLoginId(수현_아이디).get();

        // then
        assertThat(foundMember.getId()).isEqualTo(수현_DB.getId());
    }
}
