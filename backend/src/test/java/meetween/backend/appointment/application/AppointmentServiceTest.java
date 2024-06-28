package meetween.backend.appointment.application;

import static meetween.backend.support.fixture.common.AppointmentFixtures.*;
import static meetween.backend.support.fixture.common.AppointmentUserFixtures.민성약속_수현유저;
import static meetween.backend.support.fixture.common.AppointmentUserFixtures.수현약속_수현유저;
import static meetween.backend.support.fixture.common.LocationFixtures.수현약속_인하대학교;
import static meetween.backend.support.fixture.common.MemberFixtures.수현_유저;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import meetween.backend.appointment.domain.*;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.request.AppointmentParticipateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.category.domain.CategoryRepository;
import meetween.backend.location.domain.LocationRepository;
import meetween.backend.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private InviteCodeRepository inviteCodeRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private AppointmentUserRepository appointmentUserRepository;

    @DisplayName("약속을 생성한다.")
    @Test
    void 약속을_생성한다() {
        // given
        AppointmentCreateRequest request = 수현_약속_생성_요청;
        given(memberRepository.getById(anyLong()))
                .willReturn(수현_유저());

        // when
        final AppointmentResponse actual = appointmentService.save(1L, request);

        // then
        assertThat(actual.getTitle()).isEqualTo(수현_약속_제목);
    }

    @DisplayName("초대코드를 통해 약속에 참여한다")
    @Test
    void 초대코드를_통해_약속에_참여한다() {
        //given
        AppointmentParticipateRequest request = 수현_약속_참여_요청;
        given(memberRepository.getById(anyLong()))
                .willReturn(수현_유저());
        given(appointmentRepository.getByInviteCode(anyLong()))
                .willReturn(수현_약속());
        given(locationRepository.getChoicedLocationByAppointment(any(Appointment.class)))
                .willReturn(수현약속_인하대학교());;

        //when
        final AppointmentResponse actual = appointmentService.participate(1L, request);

        //then
        assertThat(actual.getTitle()).isEqualTo(수현_약속_제목);
    }

    @DisplayName("회원을 통해 모든 회원-약속을 조회한다.")
    @Test
    void 회원을_통해_모든_회원_약속을_조회한다() {
        //given
        PageRequest pageRequest = PageRequest.of(1, 1);
        List<AppointmentUser> appointmentUsers = List.of(수현약속_수현유저(), 민성약속_수현유저());
        Page<AppointmentUser> appointmentPage = new PageImpl<>(appointmentUsers, pageRequest, appointmentUsers.size());

        given(memberRepository.getById(anyLong()))
                .willReturn(수현_유저());
        given(appointmentUserRepository.findAllByMember(any(), any()))
                .willReturn(appointmentPage);
        given(locationRepository.getChoicedLocationByAppointment(any(Appointment.class)))
                .willReturn(수현약속_인하대학교());

        //when
        final IntegratedAppointmentResponses actual = appointmentService.findAll(1L, pageRequest);

        //then
        assertThat(actual.getAppointmentResponses()).hasSize(2);
        assertThat(actual.getTotalPages()).isEqualTo(2);
    }
}