package meetween.backend.appointment.presentation;

import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.request.AppointmentParticipateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static meetween.backend.support.fixture.common.AppointmentFixtures.*;
import static meetween.backend.support.fixture.common.CategoryFixtures.스터디_카테고리_제목;
import static meetween.backend.support.fixture.common.CategoryFixtures.스터디_카테고리_컬러_문자;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AppointmentControllerTest extends ControllerTest {

    @DisplayName("약속을 생성하면 상태코드 201을 반환한다.")
    @Test
    void 약속을_생성하면_상태코드_201을_반환한다() throws Exception {
        // given
        AppointmentCreateRequest request = new AppointmentCreateRequest(수현_약속_제목, 하루_뒤_시간, 수현_약속_위도, 수현_약속_경도, 3L, 스터디_카테고리_제목, 스터디_카테고리_컬러_문자);
        AppointmentResponse response = new AppointmentResponse(수현_약속());
        given(appointmentService.save(any(), any())).willReturn(response);
        given(authArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(1L);

        // when & then
        mockMvc.perform(post("/appointment")
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("약속 참여에 성공하면 200을 반환한다.")
    @Test
    void 약속_참여에_성공하면_200을_반환한다() throws Exception {
        // given
        Long inviteCode = 123456L;
        AppointmentParticipateRequest request = new AppointmentParticipateRequest(inviteCode);
        AppointmentResponse response = new AppointmentResponse(수현_약속());

        given(appointmentService.participate(any(), any())).willReturn(response);
        given(authArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(1L);

        // when & then
        mockMvc.perform(post("/appointment/participate")
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}