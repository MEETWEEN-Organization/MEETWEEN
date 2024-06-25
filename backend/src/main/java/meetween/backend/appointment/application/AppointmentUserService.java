package meetween.backend.appointment.application;

import meetween.backend.appointment.domain.*;
import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.appointment.exception.NotAdminMemberException;
import meetween.backend.category.domain.CategoryRepository;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
public class AppointmentUserService {

    private final AppointmentUserRepository appointmentUserRepository;
    private final MemberRepository memberRepository;
    private final AppointmentRepository appointmentRepository;
    private final CategoryRepository categoryRepository;

    public AppointmentUserService(final CategoryRepository categoryRepository, final AppointmentUserRepository appointmentUserRepository, final MemberRepository memberRepository, final AppointmentRepository appointmentRepository) {
        this.appointmentUserRepository = appointmentUserRepository;
        this.appointmentRepository = appointmentRepository;
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void updateAuthority(Long appointmentId, Long loginMemberId, Long targetMemberId) {
        try {
            Appointment appointment = appointmentRepository.getById(appointmentId);
            List<AppointmentUser> appointmentUserList = appointmentUserRepository.findByAppointmentWithOptimisticLock(appointment);
            AppointmentUser targetAppointmentUser = getAppointmentUser(targetMemberId, appointmentUserList);

            MemberAuthority loginMemberAuthority = getMemberAuthority(loginMemberId, appointmentUserList);
            validateIsAdminMember(loginMemberAuthority);

            targetAppointmentUser.updateAuthority(targetAppointmentUser.getMemberAuthority());
        } catch (ObjectOptimisticLockingFailureException e) { // 동시성 문제 발생했을 때
            throw new NotAdminMemberException("회원님의 권한이 변경되어 권한 수정이 불가능합니다.");
        }
    }

    private AppointmentUser getAppointmentUser(Long targetMemberId, List<AppointmentUser> appointmentUserList) {
        for (AppointmentUser appointmentUser : appointmentUserList) {
            if (Objects.equals(appointmentUser.getMember().getId(), targetMemberId)) {
                return appointmentUser;
            }
        }
        throw new NoExistAppointmentUserException();
    }

    private MemberAuthority getMemberAuthority(Long memberId, List<AppointmentUser> appointmentUserList) {
        for (AppointmentUser appointmentUser : appointmentUserList) {
            if (Objects.equals(appointmentUser.getMember().getId(), memberId)) {
                return appointmentUser.getMemberAuthority();
            }
        }
        throw new NoExistAppointmentUserException();
    }

    private void validateIsAdminMember(MemberAuthority memberAuthority) {
        if (memberAuthority != MemberAuthority.ADMIN) {
            throw new NotAdminMemberException();
        }
    }
}
