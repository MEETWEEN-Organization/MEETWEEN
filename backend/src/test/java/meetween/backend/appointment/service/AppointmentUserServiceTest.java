package meetween.backend.appointment.service;

import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;
import static meetween.backend.support.fixture.common.AppointmentUserFixtures.수현약속_민성유저;
import static meetween.backend.support.fixture.common.AppointmentUserFixtures.수현약속_수현유저;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.domain.AppointmentUserRepository;
import meetween.backend.appointment.exception.NotAdminMemberException;
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
public class AppointmentUserServiceTest {

    @InjectMocks
    private AppointmentUserService appointmentUserService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AppointmentUserRepository appointmentUserRepository;

    private Member mockMemberA;
    private Member mockMemberB;

    @BeforeEach
    void setUp() {
        mockMemberA = Mockito.spy(MemberFixtures.수현_유저());
        when(mockMemberA.getId()).thenReturn(1L);

        mockMemberB = Mockito.spy(MemberFixtures.민성_유저());
        when(mockMemberB.getId()).thenReturn(2L);
    }

    @DisplayName("약속내 다른 유저의 권한을 변경한다.")
    @Test
    void 약속내_다른_유저의_권한을_변경한다() {
        //given
        given(memberRepository.getById(1L))
                .willReturn(mockMemberA);
        given(memberRepository.getById(2L))
                .willReturn(mockMemberB);
        given(appointmentRepository.getById(anyLong()))
                .willReturn(수현_약속());
        given(appointmentUserRepository.getByMemberAndAppointment(eq(mockMemberA), any()))
                .willReturn(수현약속_수현유저());
        given(appointmentUserRepository.getByMemberAndAppointment(eq(mockMemberB), any()))
                .willReturn(수현약속_민성유저());

        //when, then
        assertThatCode(() -> appointmentUserService.updateAuthority(1L, mockMemberA.getId(), mockMemberB.getId()))
                .doesNotThrowAnyException();
    }

    @DisplayName("어드민이 아닌 유저가 다른 유저의 권한을 변경 시도하면 예외를 발생시킨다.")
    @Test
    void 어드민이_아닌_유저가_다른_유저의_권한을_변경_시도하면_예외를_발생시킨다() {
        //given
        given(memberRepository.getById(1L))
                .willReturn(mockMemberA);
        given(memberRepository.getById(2L))
                .willReturn(mockMemberB);
        given(appointmentRepository.getById(anyLong()))
                .willReturn(수현_약속());
        given(appointmentUserRepository.getByMemberAndAppointment(eq(mockMemberA), any()))
                .willReturn(수현약속_수현유저());
        given(appointmentUserRepository.getByMemberAndAppointment(eq(mockMemberB), any()))
                .willReturn(수현약속_민성유저());

        //when, then
        assertThatThrownBy(() -> appointmentUserService.updateAuthority(1L, mockMemberB.getId(), mockMemberA.getId()))
                .isInstanceOf(NotAdminMemberException.class);
    }
}
