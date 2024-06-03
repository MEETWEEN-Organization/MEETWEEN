package meetween.backend.category.application;

import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final AppointmentRepository appointmentRepository;
    private final MemberRepository memberRepository;

    public CategoryService(final AppointmentRepository appointmentRepository, final MemberRepository memberRepository) {
        this.appointmentRepository = appointmentRepository;
        this.memberRepository = memberRepository;
    }

    public IntegratedAppointmentResponses findByCategory(Long memberId, String categoryName) {
        Member member = memberRepository.getById(memberId);
        return new IntegratedAppointmentResponses(appointmentRepository.findByUserAndCategoryName(member, categoryName).stream()
                .map(AppointmentResponse::new)
                .collect(Collectors.toList()));
    }
}
