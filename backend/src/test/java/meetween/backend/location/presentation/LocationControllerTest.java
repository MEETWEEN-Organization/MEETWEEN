package meetween.backend.location.presentation;

import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;
import static meetween.backend.support.fixture.common.LocationFixtures.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.location.dto.request.LocationAddRequest;
import meetween.backend.location.exception.InvalidLocationTypeException;
import meetween.backend.location.exception.NotOnlyOneLocationException;
import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


class LocationControllerTest extends ControllerTest {

    @DisplayName("약속에 장소 후보지를 추가하면 상태코드 200을 반환한다.")
    @Test
    void 약속에_장소_후보지를_추가하면_상태코드_200을_반환한댜() throws Exception {
        //given
        Long appointmentId = 1L;
        LocationAddRequest request = new LocationAddRequest(인하대학교_위도, 인하대학교_경도);
        AppointmentResponse response = new AppointmentResponse(수현_약속(), 수현약속_인하대학교());
        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));
        given(locationService.addLocation(any(), any(), any())).willReturn(response);

        //when, then
        mockMvc.perform(post("/location/{appointmentId}", appointmentId)
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("약속에 속하지 않은 멤버가 요청을 보내면 상태코드 404를 반환한다.")
    @Test
    void 약속에_속하지_않은_멤버가_요청을_보내면_상태코드_404를_반환한다() throws Exception {
        //given
        Long appointmentId = 1L;
        LocationAddRequest request = new LocationAddRequest(인하대학교_위도, 인하대학교_경도);

        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));
        willThrow(new NoExistAppointmentUserException())
                .given(locationService)
                .addLocation(any(), any(), any());

        //when, then
        mockMvc.perform(post("/location/{appointmentId}", appointmentId)
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("메인 장소를 변경하면 상태코드 200을 반환한다.")
    @Test
    void 메인_장소를_변경하면_상태코드_200을_반환한다() throws Exception{
        //given
        Long appointmentId = 1L;
        Long locationId = 1L;
        AppointmentResponse response = new AppointmentResponse(수현_약속(), 수현약속_인하대학교());
        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));
        given(locationService.changeMain(anyLong(), anyLong(), anyLong())).willReturn(response);

        //when,then
        mockMvc.perform(post("/location/{appointmentId}/change-main/{locationId}", appointmentId, locationId)
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("한 약속 내에 메인 장소가 2개 이상이라면 상태코드 400을 반환한다.")
    @Test
    void 한_약속_내에_메인_장소가_2개_이상이라면_상태코드_400을_반환한다() throws Exception{
        //given
        Long appointmentId = 1L;
        Long locationId = 1L;
        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));
        willThrow(new NotOnlyOneLocationException())
                .given(locationService)
                .changeMain(anyLong(), anyLong(), anyLong());

        //when,then
        mockMvc.perform(post("/location/{appointmentId}/change-main/{locationId}", appointmentId, locationId)
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("이미 메인인 장소를 변경하려고 하면 상태코드 400을 반환한다.")
    @Test
    void 이미_메인인_장소를_변경하려고_하면_상태코드_400을_반환한다() throws Exception{
        //given
        Long appointmentId = 1L;
        Long locationId = 1L;
        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));
        willThrow(new InvalidLocationTypeException())
                .given(locationService)
                .changeMain(anyLong(), anyLong(), anyLong());

        //when,then
        mockMvc.perform(post("/location/{appointmentId}/change-main/{locationId}", appointmentId, locationId)
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}