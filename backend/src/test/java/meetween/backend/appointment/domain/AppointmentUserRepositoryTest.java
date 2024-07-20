package meetween.backend.appointment.domain;

import static meetween.backend.support.fixture.common.AppointmentFixtures.*;
import static meetween.backend.support.fixture.common.MemberFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.global.config.JpaAuditConfig;
import meetween.backend.invitecode.domain.InviteCode;
import meetween.backend.location.domain.InviteCodeRepository;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import meetween.backend.member.domain.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest
@Import(JpaAuditConfig.class)
@ActiveProfiles("test")
class AppointmentUserRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentUserRepository appointmentUserRepository;

    @Autowired
    private InviteCodeRepository inviteCodeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("특정 유저가 속한 모든 약속-유저를 찾아 페이징한다.")
    @Test
    void 특정_유저가_속한_모든_약속_유저를_찾아_페이징한다() {
        //given
        PageRequest pageRequest = PageRequest.of(0, 1);

        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);

        InviteCode inviteCode1 = new InviteCode(123456L);
        Appointment appointment1 = new Appointment(수현_약속_제목, inviteCode1, 하루_뒤_시간, 3L);
        AppointmentUser appointmentUser1 = new AppointmentUser(appointment1, member, MemberAuthority.ADMIN);

        InviteCode inviteCode2 = new InviteCode(654321L);
        Appointment appointment2 = new Appointment(민성_약속_제목, inviteCode2, 하루_뒤_시간, 3L);
        AppointmentUser appointmentUser2 = new AppointmentUser(appointment2, member, MemberAuthority.ADMIN);

        memberRepository.save(member);
        inviteCodeRepository.save(inviteCode1);
        inviteCodeRepository.save(inviteCode2);
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentUserRepository.save(appointmentUser1);
        appointmentUserRepository.save(appointmentUser2);

        //when
        Page<AppointmentUser> actual = appointmentUserRepository.findAllByMember(member, pageRequest);

        //then
        assertThat(actual).hasSize(1);
    }

    @DisplayName("멤버와 약속을 통해 약속-유저를 찾는다.")
    @Test
    void 멤버와_약속을_통해_약속_유저를_찾는다() {
        //given
        InviteCode inviteCode = new InviteCode(123456L);
        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);
        Appointment appointment = new Appointment(수현_약속_제목, inviteCode, 하루_뒤_시간, 3L);
        AppointmentUser appointmentUser = new AppointmentUser(appointment, member, MemberAuthority.ADMIN);

        memberRepository.save(member);
        inviteCodeRepository.save(inviteCode);
        appointmentRepository.save(appointment);
        appointmentUserRepository.save(appointmentUser);

        //when
        AppointmentUser actual = appointmentUserRepository.getByMemberAndAppointment(member, appointment);

        //then
        assertThat(actual).isEqualTo(appointmentUser);
    }

    @DisplayName("입력받은 멤버와 약속을 가진 약속-유저가 없으면 예외를 발생시킨다.")
    @Test
    void 입력받은_멤버와_약속을_가진_약속_유저가_없으면_예외를_발생시킨다() {
        //given
        InviteCode inviteCode = new InviteCode(123456L);
        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);
        Appointment appointment = new Appointment(수현_약속_제목, inviteCode, 하루_뒤_시간, 3L);

        memberRepository.save(member);
        inviteCodeRepository.save(inviteCode);
        appointmentRepository.save(appointment);

        //when, then
        assertThatThrownBy(() -> appointmentUserRepository.getByMemberAndAppointment(member, appointment))
                .isInstanceOf(NoExistAppointmentUserException.class);
    }

    @DisplayName("약속과 멤버를 통해 해당하는 약속-유저가 있다면 참을 반환한다.")
    @Transactional
    @Test
    void 약속과_멤버를_통해_해당하는_약속_유저가_있다면_참을_반환한다() {
        //given
        InviteCode inviteCode = new InviteCode(123456L);
        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);
        Appointment appointment = new Appointment(수현_약속_제목, inviteCode, 하루_뒤_시간, 3L);
        AppointmentUser appointmentUser = new AppointmentUser(appointment, member, MemberAuthority.ADMIN);

        memberRepository.save(member);
        inviteCodeRepository.save(inviteCode);
        appointmentRepository.save(appointment);
        appointmentUserRepository.save(appointmentUser);

        //when
        boolean actual = appointmentUserRepository.existsByAppointmentAndMember(appointment, member);

        //when
        assertThat(actual).isEqualTo(true);
    }

    @DisplayName("약속과 멤버를 통해 해당하는 약속-유저가 없다면 거짓을 반환한다.")
    @Transactional
    @Test
    void 약속과_멤버를_통해_해당하는_약속_유저가_없다면_거짓을_반환한다() {
        //given
        InviteCode inviteCode = new InviteCode(123456L);
        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);
        Appointment appointment = new Appointment(수현_약속_제목, inviteCode, 하루_뒤_시간, 3L);

        memberRepository.save(member);
        inviteCodeRepository.save(inviteCode);
        appointmentRepository.save(appointment);

        //when
        boolean actual = appointmentUserRepository.existsByAppointmentAndMember(appointment, member);

        //when
        assertThat(actual).isEqualTo(false);
    }
}