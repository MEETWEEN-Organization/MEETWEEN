package meetween.backend.auth.service;

import static org.assertj.core.api.Assertions.assertThat;

import meetween.backend.config.TestConfig;
import meetween.backend.user.dto.TokenResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import meetween.backend.user.service.AuthService;

@SpringBootTest(classes = TestConfig.class)
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @DisplayName("카카오 소셜 로그인을 위한 링크를 생성한다.")
    @Test
    void 카카오_소셜_로그인을_위한_링크를_생성한다() {
        // given
        String kakaoLink = authService.getSocialLink();

        // when, then
        assertThat(kakaoLink).isNotEmpty();
    }

    @DisplayName("토큰 생성을 하면 OAuth 서버에서 인증 후 토큰을 반환한다")
    @Test
    void 토큰_생성을_하면_OAuth_서버에서_인증_후_토큰을_반환한다() {
        // given
        String code = "authorization code";

        // when
        TokenResponse tokenResponse = authService.generateTokenWithCode(code);

        // then
        assertThat(tokenResponse.getAccessToken()).isNotEmpty();
    }
}
