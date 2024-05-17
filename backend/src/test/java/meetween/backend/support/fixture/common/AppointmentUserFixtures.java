package meetween.backend.support.fixture.common;

import meetween.backend.appointment.domain.AppointmentUser;
import meetween.backend.appointment.domain.MemberAuthority;

import static meetween.backend.support.fixture.common.AppointmentFixtures.민성_약속;
import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;
import static meetween.backend.support.fixture.common.MemberFixtures.민성_유저;
import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;

public class AppointmentUserFixtures {

    public static AppointmentUser 수현약속_수현유저() {
        return new AppointmentUser(수현_약속(), 수현_유저(), MemberAuthority.ADMIN);
    }

    public static AppointmentUser 수현약속_민성유저() {
        return new AppointmentUser(수현_약속(), 민성_유저(), MemberAuthority.NORMAL);
    }

    public static AppointmentUser 민성약속_수현유저() {
        return new AppointmentUser(민성_약속(), 수현_유저(), MemberAuthority.NORMAL);
    }
}
