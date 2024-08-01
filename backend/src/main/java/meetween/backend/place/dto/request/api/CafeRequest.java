package meetween.backend.place.dto.request.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.entity.Cafe;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CafeRequest {

    @JsonProperty("LOCALDATA_072405")
    private PlaceInfoRequest localData;

    public List<Cafe> toCafes() {
        return localData.toPlaces(Cafe.class);
    }

    public int getTotalCount() {
        return localData.getTotalCount();
    }
}
