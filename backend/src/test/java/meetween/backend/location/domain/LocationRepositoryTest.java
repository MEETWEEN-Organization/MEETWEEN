package meetween.backend.location.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.global.config.JpaAuditConfig;
import meetween.backend.location.exception.NoExistLocationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@DataJpaTest
@Import(JpaAuditConfig.class)
class LocationRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @DisplayName("특정 약속 내의 선택된 장소를 반환한다.")
    @Test
    void 특정_약속_내의_선택된_장소를_반환한다() {
        //given
        Appointment appointment = new Appointment("수현의 약속", 123456L, LocalDateTime.now().plusDays(1), 3L);
        Location location1 = new Location(appointment, BigDecimal.valueOf(37.450354677762), BigDecimal.valueOf(126.65915614333), LocationType.CHOICED);
        Location location2 = new Location(appointment, BigDecimal.valueOf(39.450354677762), BigDecimal.valueOf(129.65915614333), LocationType.PROPOSED);

        appointmentRepository.save(appointment);
        locationRepository.save(location1);
        locationRepository.save(location2);

        //when
        Location actual = locationRepository.getChoicedLocationByAppointment(appointment);

        //then
        assertThat(actual.getLocationType()).isEqualTo(LocationType.CHOICED);
    }

    @DisplayName("특정 약속내에 선택된 장소가 없다면 예외를 발생시킨다.")
    @Test
    void 특정_약속내에_선택된_장소가_없다면_예외를_발생시킨다() {
        //given
        Appointment appointment = new Appointment("수현의 약속", 123456L, LocalDateTime.now().plusDays(1), 3L);

        appointmentRepository.save(appointment);

        //when,then
        assertThatThrownBy(() -> locationRepository.getChoicedLocationByAppointment(appointment))
                .isInstanceOf(NoExistLocationException.class);
    }
}