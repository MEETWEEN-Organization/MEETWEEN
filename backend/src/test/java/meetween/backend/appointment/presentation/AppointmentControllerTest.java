package meetween.backend.appointment.presentation;

import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static meetween.backend.support.fixture.AppointmentFixtures.수현_약속_요청;
import static meetween.backend.support.fixture.AppointmentFixtures.수현_약속_응답;
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
        given(appointmentService.save(any(), any())).willReturn(수현_약속_응답);
        given(authArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(1L);

        // when
        mockMvc.perform(post("/appointment")
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(수현_약속_요청)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}