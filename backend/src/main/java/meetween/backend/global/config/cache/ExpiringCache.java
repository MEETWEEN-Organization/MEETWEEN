package meetween.backend.global.config.cache;

import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ExpiringCache extends ConcurrentMapCache {

    private final Map<Object, LocalDateTime> expireMap = new ConcurrentHashMap<>();
    private final long expireAfter;

    public ExpiringCache(final String name, final long expireAfter) {
        super(name);
        this.expireAfter = expireAfter;
    }

    @Override
    public Object lookup(final Object key) {
        LocalDateTime expiredTime = expireMap.get(key);
        if (Objects.isNull(expiredTime) || isValidCache(expiredTime)) {
            return super.lookup(key);
        }

        expireMap.remove(key);
        super.evict(key);
        return null;
    }

    @Override
    public void put(final Object key, final Object value) {
        expireMap.put(key, LocalDateTime.now().plusSeconds(expireAfter));
        super.put(key, value);
    }

    public void evictExpiredCache() {
        ConcurrentMap<Object, Object> nativeCache = getNativeCache();

        nativeCache.keySet()
                .stream()
                .filter(key -> !isValidCache(expireMap.get(key)))
                .forEach(super::evict);
    }

    private boolean isValidCache(final LocalDateTime expireTime) {
        return LocalDateTime.now().isBefore(expireTime);
    }
}
