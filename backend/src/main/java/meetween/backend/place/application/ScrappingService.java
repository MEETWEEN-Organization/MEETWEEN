package meetween.backend.place.application;

import meetween.backend.place.domain.*;
import meetween.backend.place.domain.entity.Bakery;
import meetween.backend.place.domain.entity.Cafe;
import meetween.backend.place.domain.entity.Restaurant;
import meetween.backend.place.dto.request.api.BakeryRequest;
import meetween.backend.place.dto.request.api.CafeRequest;
import meetween.backend.place.dto.request.api.RestaurantRequest;
import meetween.backend.place.infra.RestTemplateBakeryRequester;
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
    private final RestTemplateBakeryRequester bakeryRequester;
    private final CustomPlaceRepository<Restaurant> customRestaurantRepository;
    private final CustomPlaceRepository<Cafe> customCafeRepository;
    private final CustomPlaceRepository<Bakery> customBakeryRepository;

    public ScrappingService(RestTemplateRestaurantRequester restaurantRequester, RestTemplateCafeRequester cafeRequester, RestTemplateBakeryRequester bakeryRequester, CustomPlaceRepository<Restaurant> customRestaurantRepository, CustomPlaceRepository<Cafe> customCafeRepository, CustomPlaceRepository<Bakery> customBakeryRepository) {
        this.restaurantRequester = restaurantRequester;
        this.cafeRequester = cafeRequester;
        this.bakeryRequester = bakeryRequester;
        this.customRestaurantRepository = customRestaurantRepository;
        this.customCafeRepository = customCafeRepository;
        this.customBakeryRepository = customBakeryRepository;
    }

    public void scrap() {
        int totalCount = scrapRestaurantTotalCount();
        for (int iterate = START_ITERATE_NUM; iterate <= totalCount; iterate++) {
            int startIndex = (iterate * ROW_SIZE) + 1;
            scrapForStartIndex(startIndex);
        }
    }

    private int scrapRestaurantTotalCount() {
        return restaurantRequester.requestForGetTotalCount(START_INDEX_FOR_TOTAL_COUNT);
    }

    private int scrapCafeTotalCount() {
        return cafeRequester.requestForGetTotalCount(START_INDEX_FOR_TOTAL_COUNT);
    }

    private int scrapBakeryTotalCount() {
        return bakeryRequester.requestForGetTotalCount(START_INDEX_FOR_TOTAL_COUNT);
    }

    private void scrapForStartIndex(int startIndex) {
        try {
            RestaurantRequest restaurantRequest = restaurantRequester.requestRestTemplate(startIndex);
            List<Restaurant> restaurants = restaurantRequest.toRestaurants();
            customRestaurantRepository.saveAllPlacesBatch(new HashSet<>(restaurants));
        } catch (NullPointerException e) {
            log.info("[Restaurant] 최소 한 개의 API가 요청중입니다.");
        }
        try {
            CafeRequest cafeRequest = cafeRequester.requestRestTemplate(startIndex);
            List<Cafe> cafes = cafeRequest.toCafes();
            customCafeRepository.saveAllPlacesBatch(new HashSet<>(cafes));
        } catch (NullPointerException e) {
            log.info("[Cafe] 최소 한 개의 API가 요청중입니다.");
        }
        try {
            BakeryRequest bakeryRequest = bakeryRequester.requestRestTemplate(startIndex);
            List<Bakery> bakeries = bakeryRequest.toBakerys();
            customBakeryRepository.saveAllPlacesBatch(new HashSet<>(bakeries));
        } catch (NullPointerException e) {
            log.info("[Bakery] 최소 한 개의 API가 요청중입니다.");
        }
        log.info("startIndex: {}, 저장 완료", startIndex);
    }
}
