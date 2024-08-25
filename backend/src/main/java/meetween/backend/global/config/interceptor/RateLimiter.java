package meetween.backend.global.config.interceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RateLimiter {
    private static final int INTERVAL_TOKEN_REFILL_SECONDS = 10;
    private static final int INTERVAL_TOKEN_REFILL_COUNT = 20;
    private static final int MAX_BANDWIDTH = 200;

    private final Bucket intervalBucket;

    public RateLimiter() {
        Refill intervalTokenRefill = Refill.intervally(INTERVAL_TOKEN_REFILL_COUNT, Duration.ofSeconds(INTERVAL_TOKEN_REFILL_SECONDS));
        Bandwidth intervalBandwidth = Bandwidth.classic(MAX_BANDWIDTH, intervalTokenRefill);
        intervalBucket = Bucket.builder()
                .addLimit(intervalBandwidth)
                .build();
    }

    public boolean tryConsume(long numToken) {
        return intervalBucket.tryConsume(numToken);
    }

    public long getBucketCount() {
        return intervalBucket.getAvailableTokens();
    }
}
