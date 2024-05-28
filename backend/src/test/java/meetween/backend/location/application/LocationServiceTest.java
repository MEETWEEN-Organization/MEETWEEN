package meetween.backend.location.application;

import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;
import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속_제목;
import static meetween.backend.support.fixture.common.LocationFixtures.인하대학교_생성_요청;
import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.domain.AppointmentUserRepository;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.location.domain.LocationRepository;
import meetween.backend.location.dto.request.LocationAddRequest;
import meetween.backend.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private AppointmentUserRepository appointmentUserRepository;

    @DisplayName("약속 내에 장소 후보지를 추가한다.")
    @Test
    void 약속_내_장소_후보지를_추가한다() {
        //given
        LocationAddRequest reuest = 인하대학교_생성_요청;
        given(memberRepository.getById(anyLong()))
                .willReturn(수현_유저());
        given(appointmentRepository.getById(anyLong()))
                .willReturn(수현_약속());
        given(appointmentUserRepository.existsByAppointmentAndMember(any(), any()))
                .willReturn(true);

        //when
        final AppointmentResponse actual = locationService.addLocation(1L, 1L, reuest);

        //then
        assertThat(actual.getTitle()).isEqualTo(수현_약속_제목);
    }
}