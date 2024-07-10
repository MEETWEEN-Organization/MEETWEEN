package meetween.backend.appointment.presentation;

import static java.util.Arrays.asList;
import static meetween.backend.support.fixture.common.AppointmentFixtures.*;
import static meetween.backend.support.fixture.common.CategoryFixtures.스터디_카테고리_제목;
import static meetween.backend.support.fixture.common.CategoryFixtures.스터디_카테고리_컬러_문자;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.request.AppointmentParticipateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.appointment.exception.NotAdminMemberException;
import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


class AppointmentControllerTest extends ControllerTest {

    @BeforeEach
    void setUp() {

    }

    @DisplayName("약속을 생성하면 상태코드 201을 반환한다.")
    @Test
    void 약속을_생성하면_상태코드_201을_반환한다() throws Exception {
        // given
        AppointmentCreateRequest request = new AppointmentCreateRequest(수현_약속_제목, 하루_뒤_시간, 수현_약속_위도, 수현_약속_경도, 3L, 스터디_카테고리_제목, 스터디_카테고리_컬러_문자);
        AppointmentResponse response = 수현_약속_응답;
        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));
        given(appointmentService.save(any(), any())).willReturn(response);

        // when, then
        mockMvc.perform(post("/appointment")
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andDo(document("appointment/save",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("JWT 토큰")
                        )
                ))
                .andExpect(status().isCreated());
    }

    @DisplayName("약속 참여에 성공하면 200을 반환한다.")
    @Test
    void 약속_참여에_성공하면_200을_반환한다() throws Exception {
        // given
        Long inviteCode = 123456L;
        AppointmentParticipateRequest request = new AppointmentParticipateRequest(inviteCode);
        AppointmentResponse response = 수현_약속_응답;
        given(appointmentService.participate(any(), any())).willReturn(response);
        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));

        // when, then
        mockMvc.perform(post("/appointment/participate")
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andDo(document("appointment/participate",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("JWT 토큰")
                        )
                ))
                .andExpect(status().isOk());
    }

    @DisplayName("내 약속을 전부 조회하면 200을 반환한다.")
    @Test
    void 내_약속을_전부_조회하면_200을_반환한다() throws Exception {
        //given
        IntegratedAppointmentResponses response = new IntegratedAppointmentResponses(asList(수현_약속_응답, 민성_약속_응답), 2);
        given(appointmentService.findAll(any(), any())).willReturn(response);
        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));

        // when, then
        mockMvc.perform(get("/appointment/my")
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1"))
                .andDo(print())
                .andDo(document("appointment/findMyAllAppointments",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("JWT 토큰")
                        ),
                        queryParameters(
                                parameterWithName("page").description("페이지")
                        )
                ))
                .andExpect(status().isOk());
    }

    @DisplayName("ADMIN유저가 다른 유저의 권한을 변경하면 200을 반환한다.")
    @Test
    void ADMIN유저가_다른_유저의_권한을_변경하면_200을_반환한다() throws Exception {
        //given
        Long appointmentId = 1L;
        Long targetMemberId = 1L;

        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));

        mockMvc.perform(patch("/appointment/{appointmentId}/{targetMemberId}/authority", appointmentId, targetMemberId)
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("appointment/updateAuthority",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("JWT 토큰")
                        ),
                        pathParameters(
                                parameterWithName("appointmentId").description("약속 ID"),
                                parameterWithName("targetMemberId").description("상대방 멤버 ID")
                        )
                ))
                .andExpect(status().isNoContent());
    }

    @DisplayName("ADMIN이 아닌 유저가 다른 유저의 권한을 업데이트 시도하면 400을 발생시킨다.")
    @Test
    void ADMIN이_아닌_유저가_다른_유저의권한을_업데이트_시도하면_400을_발생시킨다() throws Exception{
        //given
        Long appointmentId = 1L;
        Long targetMemberId = 1L;

        given(jwtTokenProvider.getMemberId(anyString())).willReturn(String.valueOf(1L));

        willThrow(new NotAdminMemberException())
                .willDoNothing()
                .given(appointmentUserService)
                .updateAuthority(any(), any(), any());

        mockMvc.perform(patch("/appointment/{appointmentId}/{targetMemberId}/authority", appointmentId, targetMemberId)
                        .header("Authorization", "Bearer aaaaaaaa.bbbbbbbb.cccccccc")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("appointment/updateAuthority/failByNotAdminMember",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName("Authorization").description("JWT 토큰")),
                            pathParameters(
                                    parameterWithName("appointmentId").description("약속 ID"),
                                    parameterWithName("targetMemberId").description("상대방 멤버 ID")
                            )
                ))
                .andExpect(status().isBadRequest());
    }
}