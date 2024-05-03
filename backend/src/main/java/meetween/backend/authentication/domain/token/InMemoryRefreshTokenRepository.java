package meetween.backend.authentication.domain.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class InMemoryRefreshTokenRepository implements RefreshTokenRepository {
    private static final Map<Long, String> storage = new ConcurrentHashMap<>();

    @Override
    public void save(final long memberId, final String refreshToken) {
        storage.put(memberId, refreshToken);
    }
}
