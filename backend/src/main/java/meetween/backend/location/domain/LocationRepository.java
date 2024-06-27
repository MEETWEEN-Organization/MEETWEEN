package meetween.backend.location.domain;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.location.exception.NoExistLocationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByAppointment(Appointment appointment);

    @Query("select l from Location l where l.appointment = :appointment and l.locationType = 'CHOICED'")
    List<Location> findChoicedLocationByAppointment(Appointment appointment);

    default Location getChoicedLocationByAppointment(final Appointment appointment) {
        return findChoicedLocationByAppointment(appointment).stream()
                .findFirst()
                .orElseThrow(NoExistLocationException::new);
    }

    default Location getById(final Long id) {
        return findById(id)
                .orElseThrow(NoExistLocationException::new);
    }
}
