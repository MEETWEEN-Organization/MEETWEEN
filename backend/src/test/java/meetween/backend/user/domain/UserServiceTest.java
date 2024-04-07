package meetween.backend.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import meetween.backend.authentication.service.UserService;
import meetween.backend.support.fixture.UserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @DisplayName("회원을 저장한다.")
    @Test
    void 회원을_저장한다() {
        // given
        User user = UserFixtures.수현_유저_생성();

        // when
        User actual = userService.save(user);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("주어진 소셜 로그인 아이디로 가입된 회원이 있는지 확인한다.")
    @CsvSource(value = {"already-registered@naver.com,true", "not-registered@naver.com,false"})
    @ParameterizedTest
    void 주어진_소셜_로그인_아이디로_가입된_회원이_있는지_확인한다(String input, boolean expected) {
        // given
        String socialLoingId = "already-registered@naver.com";
        String profileImageUrl = "https://avatars.githubusercontent.com/u/88240193?v=4";
        String displayName = "이민성";
        User user = new User(socialLoingId, profileImageUrl, displayName, SocialType.KAKAO);
        userService.save(user);

        // when
        boolean actual = userService.existsBySocialLoginId(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
