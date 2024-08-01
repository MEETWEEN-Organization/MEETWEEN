package meetween.backend.place.dto.request.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import meetween.backend.place.domain.entity.Bakery;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BakeryRequest {

    @JsonProperty("LOCALDATA_072218")
    private PlaceInfoRequest localData;

    public List<Bakery> toBakerys() {
        return localData.toPlaces(Bakery.class);
    }

    public int getTotalCount() {
        return localData.getTotalCount();
    }
}
