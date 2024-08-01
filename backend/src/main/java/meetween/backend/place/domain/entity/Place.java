package meetween.backend.place.domain.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
public class Place {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

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

    protected Place() {}

    public Place(String id, String name, String address, String type, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
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
}
