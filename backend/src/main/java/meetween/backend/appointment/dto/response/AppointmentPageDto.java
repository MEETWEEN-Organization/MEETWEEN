package meetween.backend.appointment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AppointmentPageDto {

    private Long id;
    private Long categoryId;
    private String title;
    private LocalDateTime appointmentDateTime;
    private  Long inviteCode;
    private Long memberCount;

    protected AppointmentPageDto() {}

    public AppointmentPageDto(Long id, Long categoryId, String title, LocalDateTime appointmentDateTime, Long inviteCode, Long memberCount) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.appointmentDateTime = appointmentDateTime;
        this.inviteCode = inviteCode;
        this.memberCount = memberCount;
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

    public Long getInviteCode() {
        return inviteCode;
    }

    public Long getMemberCount() {
        return memberCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public void setInviteCode(Long inviteCode) {
        this.inviteCode = inviteCode;
    }

    public void setMemberCount(Long memberCount) {
        this.memberCount = memberCount;
    }
}
