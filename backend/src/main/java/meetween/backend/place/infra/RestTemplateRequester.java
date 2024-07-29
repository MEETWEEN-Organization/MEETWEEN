package meetween.backend.place.infra;

import meetween.backend.place.exception.ApiResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


public abstract class RestTemplateRequester<T> {
    protected static final String BASE_URL = "http://openapi.seoul.go.kr:8088";
    protected static final String DATA_TYPE = "JSON";
    protected static final int DIVIDE_TOTAL_COUNT = 1000;
    protected final String secretKey;
    protected final RestTemplate restTemplate;

    private static final int ROW_SIZE = 999;
    private static final int ONE_SECOND = 1000;
    private static final int MIN_TRY_COUNT = 0;
    private static final int MAX_TRY_COUNT = 10;
    private static final Logger log = LoggerFactory.getLogger(RestTemplateRequester.class);

    public RestTemplateRequester(RestTemplate restTemplate, @Value("${api.secret_key}") String secretKey) {
        this.restTemplate = restTemplate;
        this.secretKey = secretKey;
    }

    public T requestRestTemplate(int startIndex) {
        for (int iterate = MIN_TRY_COUNT; iterate < MAX_TRY_COUNT; iterate++) {
            try {
                int endIndex = startIndex + ROW_SIZE;
                T request = requestPlaceData(startIndex, endIndex);

                if (request != null) {
                    return request;
                }

            } catch (Exception e) {
                log.warn("페이지 요청 실패, 재시도합니다. 실행 횟수: {}, startIndex: {}, 에러메세지: {}", iterate, startIndex, e.getMessage());
                waitForRetry();
            }
        }
        throw new ApiResponseException("API 요청 횟수가 제한 범위를 초과했습니다.");
    }

    private void waitForRetry() {
        try {
            Thread.sleep(ONE_SECOND);
        } catch (InterruptedException exception) {
            throw new ApiResponseException();
        }
    }

    public abstract int requestForGetTotalCount(int startIndex);

    public abstract T requestPlaceData(int startIndex, int endIndex);

    public abstract URI generatePlaceUriWithParam(int startIndex, int endIndex);

}
