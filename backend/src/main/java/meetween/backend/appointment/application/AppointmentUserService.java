package meetween.backend.appointment.service;

import meetween.backend.appointment.domain.*;
import meetween.backend.appointment.exception.NotAdminMemberException;
import meetween.backend.authentication.dto.LoginMember;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class AppointmentUserService {

    private final AppointmentUserRepository appointmentUserRepository;
    private final MemberRepository memberRepository;
    private final AppointmentRepository appointmentRepository;

    public AppointmentUserService(final AppointmentUserRepository appointmentUserRepository, final MemberRepository memberRepository, final AppointmentRepository appointmentRepository) {
        this.appointmentUserRepository = appointmentUserRepository;
        this.appointmentRepository = appointmentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void updateAuthority(Long appointmentId, Long loginMemberId, Long targetMemberId) {
        Member loginMember = memberRepository.getById(loginMemberId);
        Member targetMember = memberRepository.getById(targetMemberId);
        Appointment appointment = appointmentRepository.getById(appointmentId);
        AppointmentUser loginAppointmentUser = appointmentUserRepository.getByMemberAndAppointment(loginMember, appointment);
        AppointmentUser targetAppointmentUser = appointmentUserRepository.getByMemberAndAppointment(targetMember, appointment);
        MemberAuthority targetAuthority = targetAppointmentUser.getMemberAuthority();

        validateIsAdminMember(loginAppointmentUser);

        targetAppointmentUser.updateAuthority(MemberAuthority.getAnotherAuthority(targetAuthority));
        appointmentUserRepository.save(targetAppointmentUser);
    }

    private void validateIsAdminMember(AppointmentUser loginAppointmentUser) {
        if (loginAppointmentUser.getMemberAuthority() == MemberAuthority.ADMIN) {
            return;
        }
        throw new NotAdminMemberException();
    }
}
