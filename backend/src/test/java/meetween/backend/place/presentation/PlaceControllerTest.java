package meetween.backend.place.presentation;

import meetween.backend.place.dto.RestaurantResponse;
import meetween.backend.place.dto.RestaurantsByLocationRequest;
import meetween.backend.support.annotation.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static meetween.backend.support.fixture.common.CoordinateFixtures.*;
import static meetween.backend.support.fixture.common.RestaurantFixtures.삼일한우국밥_응답;
import static meetween.backend.support.fixture.common.RestaurantFixtures.타임스퀘어_응답;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlaceControllerTest extends ControllerTest {

    @DisplayName("좌표를 통해 근처 식당 정보들을 가져온 후 200을 반환한다.")
    @Test
    void 좌표를_통해_근처_식당_정보들을_가져온_후_200을_반환한다() throws Exception{
        // given
        RestaurantsByLocationRequest request = new RestaurantsByLocationRequest(타임스퀘어_위도, 타임스퀘어_경도, 위도_델타, 경도_델타);
        RestaurantResponse response = new RestaurantResponse(List.of(타임스퀘어_응답, 삼일한우국밥_응답));

        given(placeService.getNearRestaurants(any())).willReturn(response);

        // when, then
        mockMvc.perform(get("/place/near")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andDo(document("place/near",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ))
                .andExpect(status().isOk());
    }
}