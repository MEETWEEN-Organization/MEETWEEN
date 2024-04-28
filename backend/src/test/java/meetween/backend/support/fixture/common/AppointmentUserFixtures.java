package meetween.backend.support.fixture.common;

import meetween.backend.appointment.domain.AppointmentUser;

import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;
import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;

public class AppointmentUserFixtures {

    public static AppointmentUser 일반_약속_유저_생성() {
        return new AppointmentUser(수현_약속(), 수현_유저());
    }
}
