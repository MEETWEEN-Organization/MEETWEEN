package meetween.backend.place.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurant",
    indexes = {
        @Index(name = "idx_restaurant__latitude_longitude", columnList = "latitude, longitude")
    }
)
public class Restaurant extends Place {

    protected Restaurant() {}

    public Restaurant(String id, String name, String address, String type, BigDecimal latitude, BigDecimal longitude) {
        super(id, name, address, type, latitude, longitude);
    }
}
