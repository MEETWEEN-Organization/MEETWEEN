package meetween.backend.authentication.domain;

import static org.assertj.core.api.Assertions.assertThat;

import meetween.backend.authentication.domain.token.InMemoryRefreshTokenRepository;
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
        assertThat(refreshTokenRepository.findById(memberId)).isEqualTo(refreshToken);
    }
}
