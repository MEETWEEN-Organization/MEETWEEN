package meetween.backend.place.infra;

import meetween.backend.place.dto.request.CafeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpMethod.GET;

@Component
public class RestTemplateCafeRequester extends RestTemplateRequester<CafeRequest> {
    private static final String CAFE_SERVICE_NAME = "LOCALDATA_072405";

    public RestTemplateCafeRequester(RestTemplate restTemplate, @Value("${api.secret_key}") String secretKey) {
        super(restTemplate, secretKey);
    }

    @Override
    public int requestForGetTotalCount(int startIndex) {
        CafeRequest request = requestPlaceData(startIndex, startIndex + 1);
        return request.getTotalCount() / DIVIDE_TOTAL_COUNT;
    }

    @Override
    public CafeRequest requestPlaceData(int startIndex, int endIndex) {
        URI uri = generatePlaceUriWithParam(startIndex, endIndex);
        ResponseEntity<CafeRequest> exchange = restTemplate.exchange(new RequestEntity<>(GET, uri), CafeRequest.class);
        return exchange.getBody();
    }

    @Override
    public URI generatePlaceUriWithParam(int startIndex, int endIndex) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .pathSegment(secretKey)
                .pathSegment(DATA_TYPE)
                .pathSegment(CAFE_SERVICE_NAME)
                .pathSegment(String.valueOf(startIndex))
                .pathSegment(String.valueOf(endIndex))
                .build()
                .toUri();
    }
}
