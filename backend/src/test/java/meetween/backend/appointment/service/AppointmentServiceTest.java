package meetween.backend.appointment.service;

import static java.util.Arrays.asList;
import static meetween.backend.support.fixture.common.AppointmentFixtures.*;
import static meetween.backend.support.fixture.common.AppointmentUserFixtures.민성약속_수현유저;
import static meetween.backend.support.fixture.common.AppointmentUserFixtures.수현약속_수현유저;
import static meetween.backend.support.fixture.common.CategoryFixtures.수현_약속_스터디_카테고리;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.domain.AppointmentUser;
import meetween.backend.appointment.domain.AppointmentUserRepository;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.request.AppointmentParticipateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.category.domain.Category;
import meetween.backend.category.domain.CategoryRepository;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import meetween.backend.support.fixture.common.MemberFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AppointmentUserRepository appointmentUserRepository;

    private Member mockMember;

    @BeforeEach
    void setUp() {
        mockMember = Mockito.spy(MemberFixtures.수현_유저());
        when(mockMember.getId()).thenReturn(1L);
    }

    @DisplayName("약속을 생성한다.")
    @Test
    void 약속을_생성한다() {
        // given
        AppointmentCreateRequest request = 수현_약속_생성_요청;
        given(memberRepository.getById(anyLong()))
                .willReturn(mockMember);
        given(categoryRepository.save(any(Category.class)))
                .willReturn(수현_약속_스터디_카테고리());
        given(appointmentRepository.save(any(Appointment.class)))
                .willReturn(수현_약속());
        given(appointmentUserRepository.save(any(AppointmentUser.class)))
                .willReturn(수현약속_수현유저());

        // when
        final AppointmentResponse actual = appointmentService.save(mockMember.getId(), request);

        // then
        assertThat(actual.getTitle()).isEqualTo(수현_약속_제목);
    }

    @DisplayName("초대코드를 통해 약속에 참여한다")
    @Test
    void 초대코드를_통해_약속에_참여한다() {
        //given
        AppointmentParticipateRequest request = 수현_약속_참여_요청;
        given(memberRepository.getById(anyLong()))
                .willReturn(mockMember);
        given(appointmentRepository.getByInviteCode(anyLong()))
                .willReturn(수현_약속());

        //when
        final AppointmentResponse actual = appointmentService.participate(mockMember.getId(), request);

        //then
        assertThat(actual.getTitle()).isEqualTo(수현_약속_제목);
    }

    @DisplayName("회원을 통해 모든 회원-약속을 조회한다.")
    @Test
    void 회원을_통해_모든_회원_약속을_조회한다() {
        //given
        given(memberRepository.getById(anyLong()))
                .willReturn(mockMember);
        given(appointmentUserRepository.findAllByMember(mockMember))
                .willReturn(asList(수현약속_수현유저(), 민성약속_수현유저()));

        //when
        final IntegratedAppointmentResponses actual = appointmentService.findAll(mockMember.getId());

        //then
        assertThat(actual.getAppointmentResponses()).hasSize(2);
    }
}