package meetween.backend.place.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "cafe",
        indexes = {
                @Index(name = "idx_latitude_longitude", columnList = "latitude, longitude")
        }
)
public class Cafe extends Place {

    protected Cafe() {}

    public Cafe(String id, String name, String adress, String type, BigDecimal latitude, BigDecimal longitude) {
        super(id, name, adress, type, latitude, longitude);
    }
}
