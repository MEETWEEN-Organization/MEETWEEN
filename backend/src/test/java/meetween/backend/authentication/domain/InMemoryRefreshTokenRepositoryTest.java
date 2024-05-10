package meetween.backend.authentication.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import meetween.backend.authentication.domain.token.InMemoryRefreshTokenRepository;
import meetween.backend.authentication.exception.NoSuchRefreshTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InMemoryRefreshTokenRepositoryTest {
    private final InMemoryRefreshTokenRepository refreshTokenRepository = new InMemoryRefreshTokenRepository();

    @DisplayName("리프레시 토큰을 저장한다.")
    @Test
    void 토큰을_저장한다() {
        // given
        long memberId = 1L;
        String refreshToken = "refresh token";

        // when
        refreshTokenRepository.save(memberId, refreshToken);

        // then
        assertThat(refreshTokenRepository.findById(1L)).isEqualTo(refreshToken);
    }

    @DisplayName("리프레시 토큰을 가져온다")
    @Test
    void 리프레시_토큰을_가져온다() {
        // given
        long memberId = 1L;
        String refreshToken = "refresh token";
        refreshTokenRepository.save(memberId, refreshToken);

        // when
        String actual = refreshTokenRepository.findById(memberId);

        // then
        assertThat(actual).isEqualTo(refreshToken);
    }

    @DisplayName("존재하지 않는 리프레시 토큰을 조회하면 거짓을 리턴한다.")
    @Test
    void 존재하지_않는_토큰을_조회하면_거짓을_리턴한다() {
        // given
        long memberId = -1L;

        //when, then
        assertThat(refreshTokenRepository.existsById(memberId)).isFalse();
    }

    @DisplayName("존재하는 리프레시 토큰을 조회하면 참을 리턴한다.")
    @Test
    void 존재하는_토큰을_조회하면_참을_리턴한다() {
        // given
        long memberId = 1L;
        String refreshToken = "refresh token";
        refreshTokenRepository.save(memberId, refreshToken);

        // when
        boolean actual = refreshTokenRepository.existsById(memberId);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("memberId 에 해당하는 토큰이 존재하지 않으면 예외가 발생한다.")
    @Test
    void memberId에_해당하는_토큰이_존재하지_않으면_예외가_발생한다() {
        // given
        long memberId = -100L;

        // when, then
        assertThatThrownBy(() -> refreshTokenRepository.findById(memberId))
                .isInstanceOf(NoSuchRefreshTokenException.class);
    }
}
