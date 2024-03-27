package meetween.backend.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import user.domain.SocialType;
import user.domain.User;
import user.exception.InvalidUserException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UserTest {
    @DisplayName("회원을 생성한다.")
    @Test
    void 회원을_생성한다() {
        // given
        String email = "msung99@gmail.com";
        String profileImageUrl = "https://avatars.githubusercontent.com/u/88240193?v=4";
        String displayName = "Haon";
        SocialType socialType = SocialType.KAKAO;

        // when, then
        assertDoesNotThrow(() -> new User(email, profileImageUrl, displayName, socialType));
    }

    @DisplayName("회원의 이메일 형식이 올바르지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"msung99@", "devhaon.com", "msung99@naver", "dev.haon"})
    void 회원을_생성한다(final String email) {
        // given
        String profileImageUrl = "https://avatars.githubusercontent.com/u/88240193?v=4";
        String displayName = "Haon";
        SocialType socialType = SocialType.KAKAO;

        // when, then
        assertThatThrownBy(() -> new User(email, profileImageUrl, displayName, socialType))
                .isInstanceOf(InvalidUserException.class)
                .hasMessage("이메일 형식이 올바르지 않습니다.");
    }

    @DisplayName("회원의 이름이 10자 이상인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "ThisIsSoLongName", "abcdefghijklmop"})
    void 회원의_이름이_10자_이하인_경우_예외가_발생한다(final String displayName) {
        // given
        String email = "msung99@gmail.com";
        String profileImageUrl = "https://avatars.githubusercontent.com/u/59357153?v=4";
        SocialType socialType = SocialType.KAKAO;

        // when & then
        assertThatThrownBy(() -> new User(email, profileImageUrl, displayName, socialType))
                .isInstanceOf(InvalidUserException.class)
                .hasMessage("이름 형식이 올바르지 않습니다.");
    }
}
