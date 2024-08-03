package meetween.backend.global.config.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    public static final String RESTAURANT = "restaurant";
    public static final String CAFE = "cafe";
    public static final String BAKERY = "bakery";
    private static final long EXPIRE_TIME = 60 * 60;

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(List.of(new ExpiringCache(RESTAURANT, EXPIRE_TIME), new ExpiringCache(CAFE, EXPIRE_TIME), new ExpiringCache(BAKERY, EXPIRE_TIME)));
        return simpleCacheManager;
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void evict() {
        ExpiringCache restaurantCache = (ExpiringCache) cacheManager().getCache(RESTAURANT);
        ExpiringCache cafeCache = (ExpiringCache) cacheManager().getCache(CAFE);
        ExpiringCache bakeryCache = (ExpiringCache) cacheManager().getCache(BAKERY);

        restaurantCache.evictExpiredCache();
        cafeCache.evictExpiredCache();
        bakeryCache.evictExpiredCache();
    }

}
