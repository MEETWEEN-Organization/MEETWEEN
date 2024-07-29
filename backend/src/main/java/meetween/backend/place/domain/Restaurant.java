package meetween.backend.place.domain;

import jakarta.persistence.*;
import meetween.backend.global.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "latitude", scale = 10, precision = 20, nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", scale = 10, precision = 20, nullable = false)
    private BigDecimal longitude;

    protected Restaurant() {}

    public Restaurant(String id, String name, String adress, String type, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.address = adress;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public String  getId() {
        return id;
    }

}
