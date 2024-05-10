package meetween.backend.authentication.domain.token;

public interface RefreshTokenRepository {
    void save(long memberId, String refreshToken);

    String findById(long memberId);

    boolean existsById(long memberId);

    long deleteById(long memberId);
}
