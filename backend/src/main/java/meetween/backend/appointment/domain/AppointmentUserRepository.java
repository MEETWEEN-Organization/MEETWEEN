package meetween.backend.appointment.domain;

import meetween.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentUserRepository extends JpaRepository<AppointmentUser, Long> {
    List<AppointmentUser> findByUser(User user);
}
