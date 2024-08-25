package meetween.backend.category.domain;

import jakarta.persistence.*;
import meetween.backend.appointment.domain.Appointment;
import meetween.backend.category.exception.InvalidCategoryException;
import meetween.backend.global.entity.BaseEntity;

@Entity
@Table(name = "category",
    indexes = {
        @Index(name = "idx_catrgory_name_appointment_id", columnList = "name, appointment_id")
    }
)
public class Category extends BaseEntity {

    private static final int MAX_NAME_LENGTH = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category_color", nullable = false)
    private CategoryColor categoryColor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    protected Category() {}

    public Category(final String name, final CategoryColor categoryColor, final Appointment appointment) {
        validateNameLength(name);
        this.name = name;
        this.categoryColor = categoryColor;
        this.appointment = appointment;
    }

    private void validateNameLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new InvalidCategoryException(String.format("카테고리 이름의 길이는 %d을 초과할 수 없습니다.", MAX_NAME_LENGTH));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
