package meetween.backend.support.fixture.common;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.request.AppointmentParticipateRequest;
import meetween.backend.category.domain.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static meetween.backend.support.fixture.common.CategoryFixtures.*;

public class AppointmentFixtures {

    /* 수현 약속 */
    public static final String 수현_약속_제목 = "수현의 약속";
    public static final Long 수현_약속_초대코드 = 123456L;
    public static final LocalDateTime 하루_뒤_시간 = LocalDateTime.now().plusDays(1);
    public static final BigDecimal 수현_약속_위도 = BigDecimal.valueOf(126.99597295767953);
    public static final BigDecimal 수현_약속_경도 = BigDecimal.valueOf(37.5280674292228);
    public static final AppointmentCreateRequest 수현_약속_생성_요청 = new AppointmentCreateRequest(수현_약속_제목, 하루_뒤_시간, 수현_약속_위도, 수현_약속_경도, 3L, 스터디_카테고리_제목, 스터디_카테고리_컬러_문자);
    public static final AppointmentParticipateRequest 수현_약속_참여_요청 = new AppointmentParticipateRequest(수현_약속_초대코드);

    public static Appointment 수현_약속() {
        Appointment appointment = new Appointment(수현_약속_제목, 수현_약속_초대코드, 하루_뒤_시간, 3L, 수현_약속_위도, 수현_약속_경도);
        appointment.updateCategory(new Category(스터디_카테고리_제목, 스터디_카테고리_컬러, appointment));
        return appointment;
    }

    /* 민성 약속 */
    public static final String 민성_약속_제목 = "수현의 약속";
    public static final Long 민성_약속_초대코드 = 654321L;
    public static final BigDecimal 민성_약속_위도 = BigDecimal.valueOf(126.99597295767953);
    public static final BigDecimal 민성_약속_경도 = BigDecimal.valueOf(37.5280674292228);
    public static final AppointmentCreateRequest 민성_약속_요청 = new AppointmentCreateRequest(민성_약속_제목, 하루_뒤_시간, 민성_약속_위도, 민성_약속_경도, 3L, 스터디_카테고리_제목, 스터디_카테고리_컬러_문자);

    public static Appointment 민성_약속() {
        Appointment appointment = new Appointment(민성_약속_제목, 민성_약속_초대코드, 하루_뒤_시간, 3L, 민성_약속_위도, 민성_약속_경도);
        appointment.updateCategory(new Category(스터디_카테고리_제목, 스터디_카테고리_컬러, appointment));
        return appointment;
    }
}
