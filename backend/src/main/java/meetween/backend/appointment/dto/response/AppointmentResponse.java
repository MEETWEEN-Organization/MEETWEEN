package meetween.backend.appointment.dto.response;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.location.domain.Location;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AppointmentResponse {

    private final Long id;
    private final Long categoryId;
    private final String title;
    private final LocalDateTime appointmentDateTime;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final Long inviteCode;
    private final Long memberCount;

    public AppointmentResponse(final Appointment appointment, final Location location) {
        this.id = appointment.getId();
        this.categoryId = appointment.getCategory().getId();
        this.title = appointment.getTitle();
        this.appointmentDateTime = appointment.getAppointmentDateTime();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.inviteCode = appointment.getInviteCode().getCode();
        this.memberCount = appointment.getMemberCount();
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Long getInviteCode() {
        return inviteCode;
    }

    public Long getMemberCount() {
        return memberCount;
    }
}
