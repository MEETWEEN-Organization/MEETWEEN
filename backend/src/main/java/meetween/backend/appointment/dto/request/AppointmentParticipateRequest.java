package meetween.backend.appointment.dto.request;

import jakarta.validation.constraints.NotNull;

public class AppointmentParticipateRequest {

    @NotNull(message = "Null일 수 없습니다.")
    private Long inviteCode;

    private AppointmentParticipateRequest() {
    }

    public AppointmentParticipateRequest(final Long inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Long getInviteCode() {
        return inviteCode;
    }
}
