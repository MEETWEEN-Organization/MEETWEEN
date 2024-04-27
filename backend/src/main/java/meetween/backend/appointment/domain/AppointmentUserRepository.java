package meetween.backend.appointment.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentUserRepository extends JpaRepository<AppointmentUser, Long> {
}
