package meetween.backend.location.domain;

import jakarta.persistence.*;
import meetween.backend.appointment.domain.Appointment;
import meetween.backend.global.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "loaction")
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apppointment_id", nullable = false)
    private Appointment appointment;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "location_type", nullable = false)
    private LocationType locationType;

    protected Location() {
    }

    public Location(final Appointment appointment, final BigDecimal latitude, final BigDecimal longitude) {
        this.appointment = appointment;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
