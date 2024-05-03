package meetween.backend.authentication.domain.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRefreshTokenRepository {
    private static final Map<Long, String> storage = new ConcurrentHashMap<>();

    public void save(final long memberId, final String refreshToken) {
        storage.put(memberId, refreshToken);
    }
}
