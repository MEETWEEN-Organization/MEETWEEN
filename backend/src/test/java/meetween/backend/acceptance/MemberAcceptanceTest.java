package meetween.backend.acceptance;

import static meetween.backend.support.fixture.common.AuthenticationFixtures.AUTHORIZATION_CODE;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.KAKAO_OAUTH_PROVIDER;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.FAKE_SOCIAL_ID;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.FAKE_NAME;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.FAKE_IMG_URL;
import static meetween.backend.support.fixture.acceptance.MemberAcceptanceFixture.자체_토큰을_생성하고_리턴한다;
import static meetween.backend.support.fixture.acceptance.MemberAcceptanceFixture.자신의_정보를_조회한다;
import static meetween.backend.support.fixture.acceptance.status.StatusFixtures.상태코드_200이_반환된다;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import meetween.backend.acceptance.config.AcceptenceConfig;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.config.TestConfig;
import meetween.backend.member.dto.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;


@Import(TestConfig.class)
public class MemberAcceptanceTest extends AcceptenceConfig {

    @DisplayName("등록된 회원이 회원 정보를 조회하면 상태코드 200을 리턴한다.")
    @Test
    void 등록된_회원이_회원_정보를_조회하면_상태코드_200을_리턴한다() {
        // given
        TokenResponse tokenResponse = 자체_토큰을_생성하고_리턴한다(KAKAO_OAUTH_PROVIDER, AUTHORIZATION_CODE);

        // when
        ExtractableResponse<Response> response = 자신의_정보를_조회한다(tokenResponse);
        MemberResponse userResponse = response.as(MemberResponse.class);

        // then
        assertAll(() -> {
            상태코드_200이_반환된다(response);
            assertThat(userResponse.getSocialLoginId()).isEqualTo(FAKE_SOCIAL_ID);
            assertThat(userResponse.getDisplayName()).isEqualTo(FAKE_NAME);
            assertThat(userResponse.getProfileImageUrl()).isEqualTo(FAKE_IMG_URL);
        });
    }
}
