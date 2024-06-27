package meetween.backend.support.fixture.common;

import meetween.backend.location.domain.Location;
import meetween.backend.location.domain.LocationType;
import meetween.backend.location.dto.request.LocationAddRequest;

import java.math.BigDecimal;

import static meetween.backend.support.fixture.common.AppointmentFixtures.민성_약속;
import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;

public class LocationFixtures {

    /* 인하대학교 */
    public static final BigDecimal 인하대학교_위도 = BigDecimal.valueOf(37.450354677762);
    public static final BigDecimal 인하대학교_경도 = BigDecimal.valueOf(126.65915614333);

    public static final LocationAddRequest 인하대학교_생성_요청 = new LocationAddRequest(인하대학교_위도, 인하대학교_경도);

    public static Location 수현약속_인하대학교() {
        return new Location(수현_약속(), 인하대학교_위도, 인하대학교_경도, LocationType.CHOICED);
    }

    public static Location 민성약속_인하대학교() {
        return new Location(민성_약속(), 인하대학교_위도, 인하대학교_경도, LocationType.CHOICED);
    }

    /* 고려대학교 */

    public static final BigDecimal 고려대학교_위도 = BigDecimal.valueOf(37.588194705681);
    public static final BigDecimal 고려대학교_경도 = BigDecimal.valueOf(127.03402453668);

    public static Location 수현약속_고려대학교() {
        return new Location(수현_약속(), 고려대학교_위도, 고려대학교_경도, LocationType.PROPOSED);
    }

}
