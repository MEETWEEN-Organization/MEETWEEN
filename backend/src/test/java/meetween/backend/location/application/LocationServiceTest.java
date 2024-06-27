package meetween.backend.location.application;

import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;
import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속_제목;
import static meetween.backend.support.fixture.common.LocationFixtures.*;
import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.domain.AppointmentUserRepository;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.location.domain.LocationRepository;
import meetween.backend.location.dto.request.LocationAddRequest;
import meetween.backend.location.exception.InvalidLocationTypeException;
import meetween.backend.location.exception.NotOnlyOneLocationException;
import meetween.backend.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


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

    @DisplayName("메인 장소를 변경한다.")
    @Test
    void 메인_장소를_변경한다() {
        //given
        given(appointmentRepository.getById(anyLong()))
                .willReturn(수현_약속());
        given(memberRepository.getById(anyLong()))
                .willReturn(수현_유저());
        given(locationRepository.getChoicedLocationByAppointment(any()))
                .willReturn(수현약속_인하대학교());
        given(locationRepository.getById(anyLong()))
                .willReturn(수현약속_고려대학교());
        given(appointmentUserRepository.existsByAppointmentAndMember(any(), any()))
                .willReturn(true);
        given(locationRepository.findChoicedLocationByAppointment(any()))
                .willReturn(List.of(수현약속_고려대학교()));

        //when
        final AppointmentResponse actual = locationService.changeMain(1L, 1L, 1L);

        //then
        assertThat(actual.getLatitude()).isEqualTo(수현약속_고려대학교().getLatitude());
    }

    @DisplayName("이미 메인인 장소를 변경하려고 하면 예외를 발생시킨다.")
    @Test
    void 이미_메인인_장소를_변경하려고_하면_예외를_발생시킨다() {
        //given
        given(appointmentRepository.getById(anyLong()))
                .willReturn(수현_약속());
        given(memberRepository.getById(anyLong()))
                .willReturn(수현_유저());
        given(locationRepository.getChoicedLocationByAppointment(any()))
                .willReturn(수현약속_인하대학교());
        given(locationRepository.getById(anyLong()))
                .willReturn(수현약속_인하대학교());
        given(appointmentUserRepository.existsByAppointmentAndMember(any(), any()))
                .willReturn(true);

        //when,then
        assertThatThrownBy(() -> locationService.changeMain(1L, 1L, 1L))
                .isInstanceOf(InvalidLocationTypeException.class);
    }

    @DisplayName("한 약속에 메인 장소가 2개 이상일 경우 예외를 발생시킨다.")
    @Test
    void 한_약속에_메인_장소가_2개_이상일_경우_예외를_발생시킨다() {
        //given
        given(appointmentRepository.getById(anyLong()))
                .willReturn(수현_약속());
        given(memberRepository.getById(anyLong()))
                .willReturn(수현_유저());
        given(locationRepository.getChoicedLocationByAppointment(any()))
                .willReturn(수현약속_인하대학교());
        given(locationRepository.getById(anyLong()))
                .willReturn(수현약속_고려대학교());
        given(appointmentUserRepository.existsByAppointmentAndMember(any(), any()))
                .willReturn(true);

        //when,then
        assertThatThrownBy(() -> locationService.changeMain(1L, 1L, 1L))
                .isInstanceOf(NotOnlyOneLocationException.class);
    }

    @DisplayName("요청받은 약속에 속하지 않은 멤버일 경우 예외를 발생시킨다.")
    @Test
    void 요청받은_약속에_속하지_않은_멤버일_경우_예외를_발생시킨다() {
        //given
        given(appointmentRepository.getById(anyLong()))
                .willReturn(수현_약속());
        given(memberRepository.getById(anyLong()))
                .willReturn(수현_유저());

        //when,then
        assertThatThrownBy(() -> locationService.changeMain(1L, 1L, 1L))
                .isInstanceOf(NoExistAppointmentUserException.class);
    }
}