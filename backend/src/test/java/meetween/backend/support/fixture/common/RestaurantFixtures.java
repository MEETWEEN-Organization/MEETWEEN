package meetween.backend.support.fixture.common;

import meetween.backend.place.dto.RestaurantSpecificResponse;

import java.math.BigDecimal;

public class RestaurantFixtures {
    /* 타임스퀘어 */
    public static final String 타임스퀘어_아이디 = "abcde";
    public static final String 타임스퀘어_이름 = "타임스퀘어";
    public static final String 타임스퀘어_주소 = "서울특별시 영등포구 영중로 15";
    public static final String 타임스퀘어_타입 = "양식";
    public static final BigDecimal 타임스퀘어_위도 =  BigDecimal.valueOf(37.5172332566615);
    public static final BigDecimal 타임스퀘어_경도 = BigDecimal.valueOf(126.903715993779);

    public static final RestaurantSpecificResponse 타임스퀘어_응답 = new RestaurantSpecificResponse(타임스퀘어_아이디, 타임스퀘어_이름, 타임스퀘어_주소, 타임스퀘어_타입, 타임스퀘어_위도, 타임스퀘어_경도);

    /* 3일 한우 국밥 */
    public static final String 삼일한우국밥_아이디 = "abcfe";
    public static final String 삼일한우국밥_이름 = "3일 한우 국밥";
    public static final String 삼일한우국밥_주소 = "서울특별시 영등포구 영중로 25";
    public static final String 삼일한우국밥_타입 = "한식";
    public static final BigDecimal 삼일한우국밥_위도 =  BigDecimal.valueOf(37.51990388525794);
    public static final BigDecimal 삼일한우국밥_경도 = BigDecimal.valueOf(126.90404176712036);

    public static final RestaurantSpecificResponse 삼일한우국밥_응답 = new RestaurantSpecificResponse(삼일한우국밥_아이디, 삼일한우국밥_이름, 삼일한우국밥_주소, 삼일한우국밥_타입, 삼일한우국밥_위도, 삼일한우국밥_경도);

}
