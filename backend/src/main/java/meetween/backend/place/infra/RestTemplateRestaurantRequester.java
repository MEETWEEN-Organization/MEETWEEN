package meetween.backend.place.infra;

import meetween.backend.place.dto.request.api.RestaurantRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpMethod.GET;

@Component
public class RestTemplateRestaurantRequester extends RestTemplateRequester<RestaurantRequest>{
    private static final String RESTAURANT_SERVICE_NAME = "LOCALDATA_072404";

    public RestTemplateRestaurantRequester(RestTemplate restTemplate, @Value("${api.secret_key}") String secretKey) {
        super(restTemplate, secretKey);
    }

    @Override
    public int requestForGetTotalCount(int startIndex) {
        RestaurantRequest request = requestPlaceData(startIndex, startIndex + 1);
        return request.getTotalCount() / DIVIDE_TOTAL_COUNT;
    }


    @Override
    public RestaurantRequest requestPlaceData(int startIndex, int endIndex) {
        URI uri = generatePlaceUriWithParam(startIndex, endIndex);
        ResponseEntity<RestaurantRequest> exchange = restTemplate.exchange(new RequestEntity<>(GET, uri), RestaurantRequest.class);
        return exchange.getBody();
    }

    @Override
    public URI generatePlaceUriWithParam(int startIndex, int endIndex) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .pathSegment(secretKey)
                .pathSegment(DATA_TYPE)
                .pathSegment(RESTAURANT_SERVICE_NAME)
                .pathSegment(String.valueOf(startIndex))
                .pathSegment(String.valueOf(endIndex))
                .build()
                .toUri();
    }
}
