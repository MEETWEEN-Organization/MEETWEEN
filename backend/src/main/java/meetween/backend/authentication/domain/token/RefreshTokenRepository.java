package meetween.backend.authentication.domain.token;

public interface RefreshTokenRepository {
    void save(long memberId, String refreshToken);
}
