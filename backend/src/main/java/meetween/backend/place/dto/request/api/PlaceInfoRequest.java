package meetween.backend.place.dto.request.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.exception.ApiResponseException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceInfoRequest {

    @JsonProperty("list_total_count")
    private int totalCount;
    @JsonProperty("row")
    private List<PlaceApiRequest> items;

    public <T> List<T> toPlaces(Class<T> clazz) {
        return items.stream()
                .map(item -> {
                    try {
                        return item.toPlace(clazz);
                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                        throw new ApiResponseException("Api 호출 도중 문제가 발생하였습니다.");
                    }
                })
                .flatMap(Optional::stream)
                .toList();
    }

    public int getTotalCount() {
        return totalCount;
    }
}
