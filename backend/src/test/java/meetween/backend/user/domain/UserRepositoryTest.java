package meetween.backend.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import meetween.backend.support.fixture.UserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @DisplayName("중복된 로그인 아이디가 존재하는 경우 참을 리턴한다.")
    @Test
    void 중복된_이메일이_존재하는_경우_참을_리턴한다() {
        // given
        String socialLoginId = "msung99";
        String profileImageUrl = "/image.png";
        String displayName = "haon";
        User member = new User(socialLoginId, profileImageUrl, displayName, SocialType.KAKAO);
        userRepository.save(member);

        // when
        boolean actual = userRepository.existsBySocialLoginId(socialLoginId);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("소셜 로그인 아이디를 통해 회원을 찾는다.")
    @Test
    void 소셜_로그인_아이디를_통해_회원을_찾는다() {
        // given
        User memoryUser = UserFixtures.수현_유저_생성();
        User savedUser = userRepository.save(memoryUser);

        // when
        String socialLoginId = memoryUser.getSocialLoginId();
        User foundUser = userRepository.findBySocialLoginId(socialLoginId).get();

        // then
        assertThat(savedUser.getId()).isEqualTo(foundUser.getId());
    }
}
