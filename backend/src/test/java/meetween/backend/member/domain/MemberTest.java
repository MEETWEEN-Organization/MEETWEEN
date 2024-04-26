package meetween.backend.member.domain;

import static meetween.backend.support.fixture.common.UserFixtures.수현_아이디;
import static meetween.backend.support.fixture.common.UserFixtures.수현_이름;
import static meetween.backend.support.fixture.common.UserFixtures.수현_프로필_이미지;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import meetween.backend.member.exception.InvalidMemberException;

public class MemberTest {
    @DisplayName("회원을 생성한다.")
    @Test
    void 회원을_생성한다() {
        // given, when, then
        assertDoesNotThrow(() -> new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO));
    }

    @DisplayName("회원의 이름이 10자 이상인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "ThisIsSoLongName", "abcdefghijklmop"})
    void 회원의_이름이_10자_이하인_경우_예외가_발생한다(final String displayName) {
        // given, when & then
        assertThatThrownBy(() -> new Member(수현_아이디, 수현_프로필_이미지, displayName, SocialType.KAKAO))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage("이름 형식이 올바르지 않습니다.");
    }
}
