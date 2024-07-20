package meetween.backend.place.domain;

import jakarta.persistence.*;
import meetween.backend.global.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String adress;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    protected Restaurant() {}

    public Restaurant(String name, String adress, String phoneNumber, String type, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
