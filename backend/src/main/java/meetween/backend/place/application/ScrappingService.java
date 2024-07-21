package meetween.backend.place.application;

import meetween.backend.place.domain.CustomRestaurantRepository;
import meetween.backend.place.domain.Restaurant;
import meetween.backend.place.dto.RestaurantRequest;
import meetween.backend.place.infra.RestTemplatePlaceRequester;
import meetween.backend.place.infra.RestTemplateRestaurantRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ScrappingService {
    private static final int ROW_SIZE = 1000;
    private static final int START_INDEX_FOR_TOTAL_COUNT = 1;
    private static final int THREAD_COUNT = 8;
    private static final int START_ITERATE_NUM = 0;
    private static final Logger log = LoggerFactory.getLogger(ScrappingService.class);
    private final RestTemplatePlaceRequester restaurantRequester;
    private final CustomRestaurantRepository customRestaurantRepository;

    public ScrappingService(final RestTemplateRestaurantRequester restaurantRequester, final CustomRestaurantRepository customRestaurantRepository) {
        this.customRestaurantRepository = customRestaurantRepository;
        this.restaurantRequester = restaurantRequester;
    }

    public void scrap() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        int totalCount = scrapTotalCount();

        for (int iterate = START_ITERATE_NUM; iterate < totalCount; iterate++) {
            int startIndex = (iterate * ROW_SIZE) + 1;
            executorService.submit(() -> scrapForStartIndex(startIndex));
        }
    }

    private int scrapTotalCount() {
        return restaurantRequester.requestRestTemplate(START_INDEX_FOR_TOTAL_COUNT);
    }

    private void scrapForStartIndex(int startIndex) {
        RestaurantRequest request = restaurantRequester.requestRestTemplate(startIndex);

        List<Restaurant> restaurants = request.toRestaurants();

        customRestaurantRepository.saveAllRestaurantsBatch(new HashSet<>(restaurants));
        log.info("startIndex: {}, 저장 완료", startIndex);
    }
}
