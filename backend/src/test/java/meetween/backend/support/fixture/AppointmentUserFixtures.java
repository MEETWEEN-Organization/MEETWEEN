package meetween.backend.support.fixture;

import meetween.backend.appointment.domain.AppointmentUser;

import static meetween.backend.support.fixture.AppointmentFixtures.일반_약속_생성;
import static meetween.backend.support.fixture.UserFixtures.수현_유저_생성;

public class AppointmentUserFixtures {

    public static AppointmentUser 일반_약속_유저_생성() {
        return new AppointmentUser(
                일반_약속_생성(),
                수현_유저_생성()
        );
    }
}
