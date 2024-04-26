package meetween.backend.appointment.dto.response;

import meetween.backend.appointment.domain.Appointment;

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

    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.categoryId = appointment.getCategory().getId();
        this.title = appointment.getTitle();
        this.appointmentDateTime = appointment.getAppointmentDateTime();
        this.latitude = appointment.getLatitude();
        this.longitude = appointment.getLongitude();
        this.inviteCode = appointment.getInviteCode();
        this.memberCount = appointment.getMemberCount();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
