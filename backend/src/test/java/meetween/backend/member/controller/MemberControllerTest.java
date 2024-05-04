package meetween.backend.member.controller;

import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;
import static meetween.backend.support.fixture.common.MemberFixtures.수현_응답;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import meetween.backend.member.exception.NoExistMemberException;
import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class MemberControllerTest extends ControllerTest {

    @DisplayName("사용자 본인의 회원 정보를 조회한다.")
    @Test
    void 본인의_회원_정보를_조회한다() throws Exception {
        // given
        given(memberService.findById(수현_유저().getId())).willReturn(수현_응답());
        given(authArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(1L);

        // when, then
        mockMvc.perform(get("/user/about")
                        .header("Authorization", "Bearer DUMMY_TOKEN")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andDo(document("member/me",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("JWT Access Token")
                        ),
                        responseFields(
                                fieldWithPath("id").description("회원 ID"),
                                fieldWithPath("socialLoginId").description("카카오 소셜 로그인 ID"),
                                fieldWithPath("profileImageUrl").description("회원 프로필 이미지 URL"),
                                fieldWithPath("displayName").description("회원 이름")
                        )
                ))
                .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 회원 정보 조회를 시도하면 예외를 발생시킨다.")
    @Test
    void 존재하지_않는_회원_정보_조회를_시도하면_예외를_발생시킨다() throws Exception {
        // given
        given(memberService.findById(-1L)).willThrow(new NoExistMemberException());


        // when, then
        mockMvc.perform(get("/user/about")
                        .header("Authorization", "Bearer DUMMY_TOKEN")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andDo(document("member/noExistMember",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("JWT Access Token")
                        )
                ))
                .andExpect(status().isOk());
    }
}
