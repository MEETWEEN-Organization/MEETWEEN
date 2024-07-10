package meetween.backend.appointment.domain;

import jakarta.persistence.*;
import meetween.backend.appointment.exception.InvalidAppointmentException;
import meetween.backend.category.domain.Category;
import meetween.backend.global.entity.BaseEntity;
import meetween.backend.invitecode.domain.InviteCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseEntity {

    private static final int MAX_TITLE_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "appointment_date_time", nullable = false)
    private LocalDateTime appointmentDateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invite_code_id")
    private InviteCode inviteCode;

    @Column(name = "member_count", nullable = false)
    private Long memberCount;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "appointment")
    private Category category;

    @OneToMany(mappedBy = "appointment")
    private List<AppointmentUser> appointmentUsers = new ArrayList<AppointmentUser>();

    protected Appointment() {}

    public Appointment(final String title, final InviteCode inviteCode, final LocalDateTime appointmentDateTime, final Long memberCount) {
        validateTitleLength(title);
        validateDateTime(appointmentDateTime);
        validateMemberCount(memberCount);
        this.title = title;
        this.inviteCode = inviteCode;
        this.appointmentDateTime = appointmentDateTime;
        this.memberCount = memberCount;
    }

    private void validateTitleLength(final String title) {
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new InvalidAppointmentException(String.format("약속 이름의 길이는 %d을 초과할 수 없습니다.", MAX_TITLE_LENGTH));
        }
    }

    private void validateDateTime(final LocalDateTime appointmentDateTime) {
        if (appointmentDateTime.isBefore(LocalDateTime.now())) {
            throw new InvalidAppointmentException("약속 시간은 현재 시간 이후여야 합니다.");
        }
    }

    private void validateMemberCount(final Long memberCount) {
        if (memberCount > 10) {
            throw new InvalidAppointmentException("최대 약속 인원수는 10명 입니다.");
        }
        if (memberCount < 2) {
            throw new InvalidAppointmentException("최소 2명 이상이 약속에 필요합니다.");
        }
    }

    public void updateCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public InviteCode getInviteCode() {
        return inviteCode;
    }

    public Long getMemberCount() {
        return memberCount;
    }
}
