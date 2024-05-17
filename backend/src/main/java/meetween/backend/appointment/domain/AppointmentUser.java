package meetween.backend.appointment.domain;

import jakarta.persistence.*;
import meetween.backend.global.entity.BaseEntity;
import meetween.backend.member.domain.Member;

@Entity
@Table(name = "appointment_and_user")
public class AppointmentUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apppointment_id", nullable = false)
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category_color", nullable = false)
    private MemberAuthority memberAuthority;

    protected AppointmentUser() {}

    public AppointmentUser(final Appointment appointment,final Member member) {
        this.appointment = appointment;
        this.member = member;
    }

    public void updateAuthority(MemberAuthority memberAuthority) {
        this.memberAuthority = memberAuthority;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public MemberAuthority getMemberAuthority() {
        return memberAuthority;
    }
}
