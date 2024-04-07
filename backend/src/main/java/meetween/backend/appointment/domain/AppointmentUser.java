package meetween.backend.appointment.domain;

import jakarta.persistence.*;
import meetween.backend.global.entity.BaseEntity;
import meetween.backend.user.domain.User;

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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected AppointmentUser() {}

    public AppointmentUser(final Appointment appointment,final User user) {
        this.appointment = appointment;
        this.user = user;
    }
}
