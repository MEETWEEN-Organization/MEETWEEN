package meetween.backend.member.controller;

import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;
import static meetween.backend.support.fixture.common.MemberFixtures.수현_응답;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

public class MemberControllerTest extends ControllerTest {

    @DisplayName("본인의 회원 정보를 조회한다.")
    @Test
    void 본인의_회원_정보를_조회한다() throws Exception {
        // given
        given(memberService.findById(수현_유저().getId())).willReturn(수현_응답());

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
                                headerWithName("Authorization").description("JWT Acess Token")
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
}
