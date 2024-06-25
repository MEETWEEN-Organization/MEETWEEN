package meetween.backend.category.domain;

import static meetween.backend.support.fixture.common.AppointmentFixtures.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.category.exception.InvalidCategoryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CategoryTest {

    @DisplayName("카테고리를 생성한다.")
    @Test
    void 카테고리를_생성한다() {
        //given
        String name = "미트윈 약속";
        CategoryColor categoryColor = CategoryColor._9A61D2;
        Appointment appointment = new Appointment(수현_약속_제목, 수현_약속_초대코드, 하루_뒤_시간, 4L);

        //when, then
        assertDoesNotThrow(() -> new Category(name, categoryColor, appointment));
    }

    @DisplayName("카테고리의 이름이 10글자를 초과하는 경우 예외를 발생시킨다.")
    @Test
    void 카테고리의_이름이_10글자를_초과하는_경우_예외를_발생시킨다() {
        //given
        String name = "미트윈미트윈미트윈미트윈";
        CategoryColor categoryColor = CategoryColor._5B59B3;
        Appointment appointment = new Appointment(수현_약속_제목, 수현_약속_초대코드, 하루_뒤_시간, 4L);

        //when, then
        assertThatThrownBy(() -> new Category(name, categoryColor, appointment))
                .isInstanceOf(InvalidCategoryException.class);
    }
}