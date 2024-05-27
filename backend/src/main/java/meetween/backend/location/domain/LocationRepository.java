package meetween.backend.location.domain;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.location.exception.NoExistLocationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByAppointment(Long appointmentId);

    @Query("select l from Location l where l.appointment = :appointment and l.locationType = 'CHOICED'")
    Optional<Location> findChoicedLocationByAppointment(Appointment appointment);

    default Location getChoicedLocationByAppointment(Appointment appointment) {
        return findChoicedLocationByAppointment(appointment)
                .orElseThrow(NoExistLocationException::new);
    }
}
