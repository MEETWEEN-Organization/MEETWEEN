package meetween.backend.appointment.domain;

import meetween.backend.appointment.exception.InvalidAppointmentException;
import meetween.backend.category.domain.Category;
import meetween.backend.support.fixture.common.CategoryFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @DisplayName("약속을 생성한다.")
    @Test
    void 약속을_생성한다() {
        // given
        String title = "수현의 약속";
        Long inviteCode = 123456L;
        LocalDateTime appointmentDateTime = LocalDateTime.now().plusDays(1);
        Category category = CategoryFixtures.스터디_카테고리();
        Long memberCount = 3L;
        BigDecimal latitude = BigDecimal.valueOf(126.99597295767953);
        BigDecimal longitude = BigDecimal.valueOf(37.5280674292228);

        // when, then
        assertDoesNotThrow(() -> new Appointment(title, inviteCode, appointmentDateTime, category, memberCount, latitude, longitude));
    }

    @DisplayName("약속 제목의 길이가 20을 초과하는 경우 예외가 발생한다.")
    @Test
    void 약속_제목의_길이가_20을_초과하는_경우_예외가_발생한다() {
        //given
        String title = "수현의 약속의 약속의 약속의 약속의 약속의 약속";
        Long inviteCode = 123456L;
        LocalDateTime appointmentDateTime = LocalDateTime.now().plusDays(1);
        Category category = CategoryFixtures.스터디_카테고리();
        Long memberCount = 3L;
        BigDecimal latitude = BigDecimal.valueOf(126.99597295767953);
        BigDecimal longitude = BigDecimal.valueOf(37.5280674292228);

        //when, then
        assertThatThrownBy(() -> new Appointment(title, inviteCode, appointmentDateTime, category, memberCount, latitude, longitude))
                .isInstanceOf(InvalidAppointmentException.class);
    }

    @DisplayName("약속 시간이 현재 시간보다 이전일 때 예외가 발생한다.")
    @Test
    void 약속_시간이_현재_시간보다_이전일_때_예외가_발생한다() {
        //given
        String title = "수현의 약속";
        Long inviteCode = 123456L;
        LocalDateTime appointmentDateTime = LocalDateTime.now().minusDays(1);
        Category category = CategoryFixtures.스터디_카테고리();
        Long memberCount = 3L;
        BigDecimal latitude = BigDecimal.valueOf(126.99597295767953);
        BigDecimal longitude = BigDecimal.valueOf(37.5280674292228);

        //when, then
        assertThatThrownBy(() -> new Appointment(title, inviteCode, appointmentDateTime, category, memberCount, latitude, longitude))
                .isInstanceOf(InvalidAppointmentException.class);
    }

    @DisplayName("약속 인원 수가 10명을 초과하는 경우 예외가 발생한다.")
    @Test
    void 약속_인원_수가_10명을_초과하는_경우_예외가_발생한다() {
        //given
        String title = "수현의 약속";
        Long inviteCode = 123456L;
        LocalDateTime appointmentDateTime = LocalDateTime.now().minusDays(1);
        Long memberCount = 11L;
        Category category = CategoryFixtures.스터디_카테고리();
        BigDecimal latitude = BigDecimal.valueOf(126.99597295767953);
        BigDecimal longitude = BigDecimal.valueOf(37.5280674292228);

        //when, then
        assertThatThrownBy(() -> new Appointment(title, inviteCode, appointmentDateTime, category, memberCount, latitude, longitude))
                .isInstanceOf(InvalidAppointmentException.class);
    }
}