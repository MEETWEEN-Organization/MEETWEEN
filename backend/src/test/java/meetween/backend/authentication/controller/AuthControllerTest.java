package meetween.backend.authentication.controller;

import static meetween.backend.support.fixture.common.AuthenticationFixtures.토큰_응답;
import static meetween.backend.support.fixture.common.AuthenticationFixtures.토큰_생성_요청;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;


import meetween.backend.authentication.dto.TokenRequest;
import meetween.backend.authentication.exception.InvalidOAuthServiceException;
import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
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

    @DisplayName("OAuth 로그인을 하면 token과 상태코드 200을 반환한다.")
    @Test
    void OAuth_로그인을_하면_token과_상태코드_200을_반환한다() throws Exception {
        // given
        given(authService.generateTokenWithCode(any(), any())).willReturn(토큰_응답());

        // when & then
        mockMvc.perform(post("/auth/{provider}/token", "kakao")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(토큰_생성_요청())))
                .andDo(print())
                .andDo(document("auth/generate/token/success",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("provider").description("kakao")),
                        requestFields(fieldWithPath("code").type(TokenRequest.class).description("OAuth 로그인 인증 코드")),
                        responseFields(fieldWithPath("accessToken").type(JsonFieldType.STRING).description("access token"),
                                fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("refresh token"))
                ))
                .andExpect(status().isOk());
    }

    @DisplayName("OAuth 로그인에 실패하면 상태코드 500을 반환한다.")
    @Test
    void OAuth_로그인에_실패하면_상태코드_500을_반환한다() throws Exception {
        // given
        given(authService.generateTokenWithCode(any(), any())).willThrow(new InvalidOAuthServiceException());

        // when, then
        mockMvc.perform(post("/auth/{provider}/token", "kakao")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(토큰_생성_요청())))
                .andDo(print())
                .andDo(document("auth/generatoe/token/fail",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("provider").description("kakao")),
                        requestFields(fieldWithPath("code").type(JsonFieldType.STRING).description("OAuth 로그인 인증 코드"))
                )).andExpect(status().isInternalServerError());
    }
}
