package meetween.backend.support.fixture;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.category.domain.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AppointmentFixtures {
    public static Appointment 일반_약속_생성() {
        return new Appointment(
                "수현의 약속",
                LocalDateTime.now().plusDays(1),
                CategoryFixtures.스터디_카테고리_생성(),
                3L,
                BigDecimal.valueOf(126.99597295767953),
                BigDecimal.valueOf(37.5280674292228)
        );
    }
}
