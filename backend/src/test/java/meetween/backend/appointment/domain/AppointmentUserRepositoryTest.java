package meetween.backend.appointment.domain;

import static meetween.backend.support.fixture.common.AppointmentFixtures.*;
import static meetween.backend.support.fixture.common.MemberFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.global.config.JpaAuditConfig;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import meetween.backend.member.domain.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;


@DataJpaTest
@Import(JpaAuditConfig.class)
class AppointmentUserRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentUserRepository appointmentUserRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("특정 유저가 속한 모든 약속-유저를 찾는다.")
    @Test
    void 특정_유저가_속한_모든_약속_유저를_찾는다() {
        //given
        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);
        Appointment appointment1 = new Appointment(수현_약속_제목, 수현_약속_초대코드, 하루_뒤_시간, 3L, 수현_약속_위도, 수현_약속_경도);
        AppointmentUser appointmentUser1 = new AppointmentUser(appointment1, member, MemberAuthority.ADMIN);

        Appointment appointment2 = new Appointment(민성_약속_제목, 민성_약속_초대코드, 하루_뒤_시간, 3L, 민성_약속_위도, 민성_약속_경도);
        AppointmentUser appointmentUser2 = new AppointmentUser(appointment2, member, MemberAuthority.ADMIN);

        memberRepository.save(member);
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentUserRepository.save(appointmentUser1);
        appointmentUserRepository.save(appointmentUser2);

        //when
        List<AppointmentUser> actual = appointmentUserRepository.findAllByMember(member);

        //then
        assertThat(actual).hasSize(2);
    }

    @DisplayName("멤버와 약속을 통해 약속-유저를 찾는다.")
    @Test
    void 멤버와_약속을_통해_약속_유저를_찾는다() {
        //given
        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);
        Appointment appointment = new Appointment(수현_약속_제목, 수현_약속_초대코드, 하루_뒤_시간, 3L, 수현_약속_위도, 수현_약속_경도);
        AppointmentUser appointmentUser = new AppointmentUser(appointment, member, MemberAuthority.ADMIN);

        memberRepository.save(member);
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
        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);
        Appointment appointment = new Appointment(수현_약속_제목, 수현_약속_초대코드, 하루_뒤_시간, 3L, 수현_약속_위도, 수현_약속_경도);

        memberRepository.save(member);
        appointmentRepository.save(appointment);

        //when, then
        assertThatThrownBy(() -> appointmentUserRepository.getByMemberAndAppointment(member, appointment))
                .isInstanceOf(NoExistAppointmentUserException.class);
    }
}