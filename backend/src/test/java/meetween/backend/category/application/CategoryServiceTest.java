package meetween.backend.category.application;

import static meetween.backend.support.fixture.common.AppointmentFixtures.민성_약속;
import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;
import static meetween.backend.support.fixture.common.CategoryFixtures.스터디_카테고리_제목;
import static meetween.backend.support.fixture.common.LocationFixtures.수현약속_인하대학교;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.location.domain.LocationRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private MemberRepository memberRepository;

    private Member mockMember;

    @BeforeEach
    void setUp() {
        mockMember = Mockito.spy(MemberFixtures.수현_유저());
        when(mockMember.getId()).thenReturn(1L);
    }

    @DisplayName("카테고리 이름을 통해 약속들을 찾아 약속 응답을 만든다.")
    @Test
    void 카테고리_이름을_통해_약속들을_찾아_약속_응답을_만든다() {
        //given
        PageRequest pageRequest = PageRequest.of(1, 1);
        List<Appointment> appointments = List.of(수현_약속(), 민성_약속());
        Page<Appointment> appointmentPage = new PageImpl<>(appointments, pageRequest, appointments.size());

        given(memberRepository.getById(anyLong()))
                .willReturn(mockMember);
        given(appointmentRepository.findByUserAndCategoryName(any(), any(), any()))
                .willReturn(appointmentPage);
        given(locationRepository.getChoicedLocationByAppointment(any(Appointment.class)))
                .willReturn(수현약속_인하대학교());
        String categoryName = 스터디_카테고리_제목;

        //when
        final IntegratedAppointmentResponses actual = categoryService.findByCategory(mockMember.getId(), categoryName, pageRequest);

        //then
        assertThat(actual.getAppointmentResponses()).hasSize(2);
        assertThat(actual.getTotalPages()).isEqualTo(2);
    }
}