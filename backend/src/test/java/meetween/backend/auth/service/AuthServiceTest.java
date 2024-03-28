package meetween.backend.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import meetween.backend.user.service.AuthService;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @DisplayName("카카오 소셜 로그인을 위한 링크를 생성한다.")
    @Test
    void 카카오_소셜_로그인을_위한_링크를_생성한다() {
        // given
        String kakaoLink = authService.getKakaoLink();

        // when, then
        assertThat(kakaoLink).isNotEmpty();
    }
}
