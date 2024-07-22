package meetween.backend.place.infra;

import meetween.backend.place.dto.RestaurantRequest;
import meetween.backend.place.exception.ApiResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpMethod.GET;

@Component
public class RestTemplateRestaurantRequester implements RestTemplatePlaceRequester {

    private static final String BASE_URL = "http://openapi.seoul.go.kr:8088";
    private static final String DATA_TYPE = "JSON";
    private static final String SERVICE_NAME = "LOCALDATA_072404";
    private static final int ROW_SIZE = 1000;
    private static final int ONE_SECOND = 1000;
    private static final int MAX_MIN_COUNT = 0;
    private static final int MAX_TRY_COUNT = 10;
    private static final Logger log = LoggerFactory.getLogger(RestTemplateRestaurantRequester.class);

    private final RestTemplate restTemplate;
    private final String secretKey;

    public RestTemplateRestaurantRequester(RestTemplate restTemplate, @Value("${api.secret_key}") String secretKey) {
        this.restTemplate = restTemplate;
        this.secretKey = secretKey;
    }

    @Override
    public RestaurantRequest requestRestTemplate(int startIndex) {
        int tryCount = MAX_MIN_COUNT;
        while (true) {
            try {
                tryCount++;
                if (tryCount > 10) {
                    throw new ApiResponseException("API 요청 횟수가 제한 범위를 초과했습니다.");
                }

                int endIndex = startIndex + ROW_SIZE;
                RestaurantRequest request = requestRestaurantData(startIndex, endIndex);

                if (request != null) {
                    return request;
                }

            } catch (Exception e) {
                log.warn("페이지 요청 실패, 재시도합니다. 실행 횟수: {}, startIndex: {}, 에러메세지: {}", tryCount, startIndex, e.getMessage());
                waitForRetry();
            }
        }
    }

    public int requestForGetTotalCount(int startIndex) {
        RestaurantRequest request = requestRestaurantData(startIndex, startIndex + 1);
        return request.getTotalCount();
    }


    private RestaurantRequest requestRestaurantData(int startIndex, int endIndex) {
        URI uri = generateUriWithParam(startIndex, endIndex);
        ResponseEntity<RestaurantRequest> exchange = restTemplate.exchange(new RequestEntity<>(GET, uri), RestaurantRequest.class);
        return exchange.getBody();
    }

    private URI generateUriWithParam(int startIndex, int endIndex) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .path(secretKey)
                .path(DATA_TYPE)
                .path(SERVICE_NAME)
                .path(String.valueOf(startIndex))
                .path(String.valueOf(endIndex))
                .build()
                .toUri();
    }

    private void waitForRetry() {
        try {
            Thread.sleep(ONE_SECOND);
        } catch (InterruptedException exception) {
            throw new ApiResponseException();
        }
    }
}
