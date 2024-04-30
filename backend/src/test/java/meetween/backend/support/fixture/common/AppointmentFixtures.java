package meetween.backend.support.fixture.common;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;

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
    public static final AppointmentCreateRequest 수현_약속_요청 = new AppointmentCreateRequest(수현_약속_제목, 하루_뒤_시간, 수현_약속_위도, 수현_약속_경도, 3L, 스터디_카테고리_제목, 스터디_카테고리_컬러_문자);
    public static final AppointmentResponse 수현_약속_응답 = new AppointmentResponse(수현_약속());

    public static Appointment 수현_약속() {
        return new Appointment(수현_약속_제목, 수현_약속_초대코드, 하루_뒤_시간, 스터디_카테고리(), 3L, 수현_약속_위도, 수현_약속_경도);
    }

    /* 민성 약속 */
}
