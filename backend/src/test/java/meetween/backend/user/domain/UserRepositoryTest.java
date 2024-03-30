package meetween.backend.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
}
