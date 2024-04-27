package meetween.backend.member.domain;

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
        // given
        String socialLoginId = "msung99";
        String profileImageUrl = "https://avatars.githubusercontent.com/u/88240193?v=4";
        String displayName = "Haon";
        SocialType socialType = SocialType.KAKAO;

        // when, then
        assertDoesNotThrow(() -> new Member(socialLoginId, profileImageUrl, displayName, socialType));
    }

    @DisplayName("회원의 이름이 10자 이상인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "ThisIsSoLongName", "abcdefghijklmop"})
    void 회원의_이름이_10자_이하인_경우_예외가_발생한다(final String displayName) {
        // given
        String socialLoginId = "msung99";
        String profileImageUrl = "https://avatars.githubusercontent.com/u/59357153?v=4";
        SocialType socialType = SocialType.KAKAO;

        // when & then
        assertThatThrownBy(() -> new Member(socialLoginId, profileImageUrl, displayName, socialType))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage("이름 형식이 올바르지 않습니다.");
    }
}
