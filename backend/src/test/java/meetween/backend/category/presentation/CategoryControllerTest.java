package meetween.backend.category.presentation;

import static java.util.Arrays.asList;
import static meetween.backend.support.fixture.common.AppointmentFixtures.민성_약속;
import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;
import static meetween.backend.support.fixture.common.LocationFixtures.민성약속_인하대학교;
import static meetween.backend.support.fixture.common.LocationFixtures.수현약속_인하대학교;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


class CategoryControllerTest extends ControllerTest {

    @DisplayName("카테고리를 통해 로그인된 유저의 약속을 조회하면 상태코드 200을 반환한다.")
    @Test
    void 카테고리를_통해_로그인된_유저의_약속을_조회하면_상태코드_200을_반환한다() throws Exception {
        //given
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("categoryName", "스터디");
        IntegratedAppointmentResponses response = new IntegratedAppointmentResponses(asList(new AppointmentResponse(수현_약속(), 수현약속_인하대학교()), new AppointmentResponse(민성_약속(), 민성약속_인하대학교())));

        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));
        given(categoryService.findByCategory(anyLong(), anyString())).willReturn(response);

        //when,then
        mockMvc.perform(get("/category")
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(param))
                .andDo(print())
                .andExpect(status().isOk());
    }
}