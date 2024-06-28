package meetween.backend.appointment.domain;

import jakarta.persistence.*;
import meetween.backend.global.entity.BaseEntity;

@Entity
@Table(name = "invite_code")
public class InviteCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private Long code;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "inviteCode")
    private Appointment appointment;

    protected InviteCode() {}

    public InviteCode(final Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}
