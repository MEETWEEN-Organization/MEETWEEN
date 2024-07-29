package meetween.backend.place.application;

import meetween.backend.place.domain.Cafe;
import meetween.backend.place.domain.CustomCafeRepository;
import meetween.backend.place.domain.CustomRestaurantRepository;
import meetween.backend.place.domain.Restaurant;
import meetween.backend.place.dto.request.CafeRequest;
import meetween.backend.place.dto.request.RestaurantRequest;
import meetween.backend.place.infra.RestTemplateCafeRequester;
import meetween.backend.place.infra.RestTemplateRestaurantRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ScrappingService {
    private static final int ROW_SIZE = 1000;
    private static final int START_INDEX_FOR_TOTAL_COUNT = 1;
    private static final int START_ITERATE_NUM = 0;
    private static final Logger log = LoggerFactory.getLogger(ScrappingService.class);
    private final RestTemplateRestaurantRequester restaurantRequester;
    private final RestTemplateCafeRequester cafeRequester;
    private final CustomRestaurantRepository customRestaurantRepository;
    private final CustomCafeRepository customCafeRepository;

    public ScrappingService(final RestTemplateRestaurantRequester restaurantRequester, final RestTemplateCafeRequester cafeRequester, final CustomRestaurantRepository customRestaurantRepository, final CustomCafeRepository customCafeRepository) {
        this.customRestaurantRepository = customRestaurantRepository;
        this.customCafeRepository = customCafeRepository;
        this.cafeRequester = cafeRequester;
        this.restaurantRequester = restaurantRequester;
    }

    public void scrap() {
        int totalCount = scrapTotalCount();

        for (int iterate = START_ITERATE_NUM; iterate <= totalCount; iterate++) {
            int startIndex = (iterate * ROW_SIZE) + 1;
            scrapForStartIndex(startIndex);
        }
    }

    private int scrapTotalCount() {
        return restaurantRequester.requestForGetTotalCount(START_INDEX_FOR_TOTAL_COUNT);
    }

    private void scrapForStartIndex(int startIndex) {
        RestaurantRequest restaurantRequest = restaurantRequester.requestRestTemplate(startIndex);
        CafeRequest cafeRequest = cafeRequester.requestRestTemplate(startIndex);

        List<Restaurant> restaurants = restaurantRequest.toRestaurants();
        List<Cafe> cafes = cafeRequest.toCafes();

        customRestaurantRepository.saveAllRestaurantsBatch(new HashSet<>(restaurants));
        customCafeRepository.saveAllCafesBatch(new HashSet<>(cafes));
        log.info("startIndex: {}, 저장 완료", startIndex);
    }
}
