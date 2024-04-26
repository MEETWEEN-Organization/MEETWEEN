package meetween.backend.appointment.dto.request;

import jakarta.validation.constraints.NotBlank;
import meetween.backend.appointment.domain.Appointment;
import meetween.backend.category.domain.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AppointmentCreateRequest {

    @NotBlank(message = "공백일 수 없습니다")
    private String title;

    @NotBlank(message = "공백일 수 없습니다")
    private LocalDateTime appointmentDateTime;

    @NotBlank(message = "공백일 수 없습니다")
    private BigDecimal latitude;

    @NotBlank(message = "공백일 수 없습니다")
    private BigDecimal longitude;

    @NotBlank(message = "공백일 수 없습니다")
    private Long memberCount;

    @NotBlank(message = "공백일 수 없습니다")
    private String categoryName;

    @NotBlank(message = "공백일 수 없습니다")
    private String categoryColor;

    public AppointmentCreateRequest(final String title, final LocalDateTime appointmentDateTime, final BigDecimal latitude, final BigDecimal longitude, final Long memberCount, final String categoryName, final String categoryColor) {
        this.title = title;
        this.appointmentDateTime = appointmentDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.memberCount = memberCount;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
    }

    public Appointment toEntity(Category category) {
        return new Appointment(title, appointmentDateTime, category, memberCount, latitude, longitude);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryColor() {
        return categoryColor;
    }
}
