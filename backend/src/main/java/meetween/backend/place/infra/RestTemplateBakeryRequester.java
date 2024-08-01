package meetween.backend.place.infra;

import meetween.backend.place.dto.request.api.BakeryRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpMethod.GET;

@Component
public class RestTemplateBakeryRequester extends RestTemplateRequester<BakeryRequest> {
    private static final String BAKERY_SERVICE_NAME = "LOCALDATA_072218";

    public RestTemplateBakeryRequester(RestTemplate restTemplate, @Value("${api.secret_key}") String secretKey) {
        super(restTemplate, secretKey);
    }

    @Override
    public int requestForGetTotalCount(int startIndex) {
        BakeryRequest request = requestPlaceData(startIndex, startIndex + 1);
        System.out.println(request.getTotalCount() / DIVIDE_TOTAL_COUNT);
        return request.getTotalCount() / DIVIDE_TOTAL_COUNT;
    }

    @Override
    public BakeryRequest requestPlaceData(int startIndex, int endIndex) {
        URI uri = generatePlaceUriWithParam(startIndex, endIndex);
        ResponseEntity<BakeryRequest> exchange = restTemplate.exchange(new RequestEntity<>(GET, uri), BakeryRequest.class);
        return exchange.getBody();
    }

    @Override
    public URI generatePlaceUriWithParam(int startIndex, int endIndex) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .pathSegment(secretKey)
                .pathSegment(DATA_TYPE)
                .pathSegment(BAKERY_SERVICE_NAME)
                .pathSegment(String.valueOf(startIndex))
                .pathSegment(String.valueOf(endIndex))
                .build()
                .toUri();
    }
}
