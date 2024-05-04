package meetween.backend.authentication.domain.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import meetween.backend.authentication.exception.NoSuchRefreshTokenException;
import org.springframework.stereotype.Component;

@Component
public class InMemoryRefreshTokenRepository implements RefreshTokenRepository {
    private static final Map<Long, String> storage = new ConcurrentHashMap<>();

    @Override
    public void save(final long memberId, final String refreshToken) {
        storage.put(memberId, refreshToken);
    }

    @Override
    public String findById(long memberId) {
        return storage.get(memberId);
    }

    @Override
    public boolean existsById(long memberId) {
        return storage.containsKey(memberId);
    }
}
