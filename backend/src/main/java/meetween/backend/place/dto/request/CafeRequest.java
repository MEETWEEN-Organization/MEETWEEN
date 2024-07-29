package meetween.backend.place.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.Cafe;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CafeRequest {

    @JsonProperty("LOCALDATA_072405")
    private PlaceInfoRequest localData;

    public List<Cafe> toCafes() {
        return localData.toCafes();
    }

    public int getTotalCount() {
        return localData.getTotalCount();
    }
}
