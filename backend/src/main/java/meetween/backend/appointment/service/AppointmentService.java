package meetween.backend.appointment.service;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.domain.AppointmentUser;
import meetween.backend.appointment.domain.AppointmentUserRepository;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.category.domain.Category;
import meetween.backend.category.domain.CategoryColor;
import meetween.backend.category.domain.CategoryRepository;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentUserRepository appointmentUserRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    public AppointmentService(final AppointmentRepository appointmentRepository, final AppointmentUserRepository appointmentUserRepository, final CategoryRepository categoryRepository, final MemberRepository memberRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentUserRepository = appointmentUserRepository;
        this.categoryRepository = categoryRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public AppointmentResponse save(final Long memberId, final AppointmentCreateRequest request) {
        Member member = memberRepository.getById(memberId);
        Category category = categoryRepository.save(new Category(request.getCategoryName(), CategoryColor.getCategoryColor(request.getCategoryColor())));

        Appointment appointment = appointmentRepository.save(request.toEntity(category));

        appointmentUserRepository.save(new AppointmentUser(appointment, member));

        return new AppointmentResponse(appointment);
    }
}
