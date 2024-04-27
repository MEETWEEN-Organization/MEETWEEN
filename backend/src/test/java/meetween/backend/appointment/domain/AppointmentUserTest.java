package meetween.backend.appointment.domain;

import meetween.backend.support.fixture.common.AppointmentFixtures;
import meetween.backend.support.fixture.common.UserFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppointmentUserTest {

    @DisplayName("약속과 유저의 중계모델을 생성한다")
    @Test
    void 약속과_유저의_중계모델을_생성한다() {
        //when,then
        Assertions.assertDoesNotThrow(() -> new AppointmentUser(AppointmentFixtures.일반_약속_생성(), UserFixtures.수현_유저_생성()));
    }
}