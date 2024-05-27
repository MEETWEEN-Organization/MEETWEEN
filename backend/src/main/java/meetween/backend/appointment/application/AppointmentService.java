package meetween.backend.appointment.application;

import meetween.backend.appointment.domain.*;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.request.AppointmentParticipateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.category.domain.Category;
import meetween.backend.category.domain.CategoryColor;
import meetween.backend.category.domain.CategoryRepository;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

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
        Long inviteCode = createInviteCode();

        Appointment appointment = request.toEntity(inviteCode);
        Category category = new Category(request.getCategoryName(), CategoryColor.getCategoryColor(request.getCategoryColor()), appointment);
        appointment.setCategory(category);

        appointmentRepository.save(appointment);
        categoryRepository.save(category);
        appointmentUserRepository.save(new AppointmentUser(appointment, member, MemberAuthority.ADMIN));

        return new AppointmentResponse(appointment);
    }

    private Long createInviteCode() {
        long inviteCode = (long)(Math.random() * 899999) + 100000;
        while (appointmentRepository.existsByInviteCode(inviteCode)) {
            inviteCode = (long)(Math.random() * 899999) + 100000;
        }
        return inviteCode;
    }

    @Transactional
    public AppointmentResponse participate(final Long memberId, final AppointmentParticipateRequest request) {
        Member member = memberRepository.getById(memberId);
        Appointment appointment = appointmentRepository.getByInviteCode(request.getInviteCode());

        appointmentUserRepository.save(new AppointmentUser(appointment, member, MemberAuthority.NORMAL));

        return new AppointmentResponse(appointment);
    }

    public IntegratedAppointmentResponses findAll(final Long memberId) {
        return new IntegratedAppointmentResponses(
                appointmentUserRepository.findAllByMember(memberRepository.getById(memberId)).stream()
                        .map(appointmentUser -> new AppointmentResponse(appointmentUser.getAppointment()))
                        .collect(Collectors.toList())
        );
    }

}