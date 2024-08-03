package meetween.backend.place.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "bakery",
        indexes = {
                @Index(name = "idx_latitude_longitude", columnList = "latitude, longitude")
        }
)
public class Bakery extends Place {

    protected Bakery() {}

    public Bakery(String id, String name, String adress, String type, BigDecimal latitude, BigDecimal longitude) {
        super(id, name, adress, type, latitude, longitude);
    }
}
