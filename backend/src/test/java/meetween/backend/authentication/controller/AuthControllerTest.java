package meetween.backend.authentication.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

public class AuthControllerTest extends ControllerTest {

    @DisplayName("OAuth 소셜 로그인을 위한 링크와 HTTP 상태코드 값 200을 리턴한다.")
    @Test
    void OAUth_소셜_로그인을_위한_링크와_HTTP_상태코드_값_200을_리턴한다() throws Exception {
        // given
        given(authService.getSocialLink(any())).willReturn("http://localhost:8080");

        // when, then
        mockMvc.perform(get("/auth/{provider}/link", "kakao"))
                .andDo(print())
                .andDo(document("auth/generate/link",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("provider").description("kakao")),
                        responseFields(fieldWithPath("oAuthUri").type(JsonFieldType.STRING).description("OAuth 소셜 로그인 링크"))
                ))
                .andExpect(status().isOk());
    }
}
