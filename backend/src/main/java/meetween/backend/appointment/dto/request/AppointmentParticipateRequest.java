package meetween.backend.appointment.dto.request;

import jakarta.validation.constraints.NotNull;

public class AppointmentParticipateRequest {

    @NotNull(message = "Null일 수 없습니다.")
    private final Long inviteCode;

    public AppointmentParticipateRequest(final Long inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Long getInviteCode() {
        return inviteCode;
    }
}
