package meetween.backend.authentication.service;

import static org.assertj.core.api.Assertions.assertThat;

import meetween.backend.config.TestConfig;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import meetween.backend.authentication.service.AuthService;

@SpringBootTest(classes = TestConfig.class)
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("카카오 소셜 로그인을 위한 링크를 생성한다.")
    @Test
    void 카카오_소셜_로그인을_위한_링크를_생성한다() {
        // given
        String kakaoLink = authService.getSocialLink();

        // when, then
        assertThat(kakaoLink).isNotEmpty();
    }

    @DisplayName("토큰 생성을 하면 OAuth 서버에서 인증 후 토큰을 반환한다.")
    @Test
    void 토큰_생성을_하면_OAuth_서버에서_인증_후_토큰을_반환한다() {
        // given
        String code = "authorization code";

        // when
        TokenResponse tokenResponse = authService.generateTokenWithCode(code);

        // then
        assertThat(tokenResponse.getAccessToken()).isNotEmpty();
    }


    @DisplayName("authorization code를 받으면 회원이 데이터베이스에 저장된다.")
    @Test
    void authorization_code를_받으면_회원이_데이터베이스에_저장된다() {
        // given
        String authorizationCode = "authorization_code";
        authService.generateTokenWithCode(authorizationCode);

        // when
        // actual = StubOAuthClient 가 반환하는 socialLoginId
        boolean actual = userRepository.existsBySocialLoginId("Fake Social Login Id");

        // then
        assertThat(actual).isTrue();
    }
}
