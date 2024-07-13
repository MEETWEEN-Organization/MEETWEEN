package meetween.backend.appointment.domain;

import static meetween.backend.support.fixture.common.MemberFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import meetween.backend.appointment.exception.NoExistAppointmentException;
import meetween.backend.category.domain.Category;
import meetween.backend.category.domain.CategoryColor;
import meetween.backend.category.domain.CategoryRepository;
import meetween.backend.global.config.JpaAuditConfig;
import meetween.backend.invitecode.domain.InviteCode;
import meetween.backend.location.domain.InviteCodeRepository;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import meetween.backend.member.domain.SocialType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;


@DataJpaTest
@Import(JpaAuditConfig.class)
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private InviteCodeRepository inviteCodeRepository;

    @Autowired
    private AppointmentUserRepository appointmentUserRepository;

    @DisplayName("초대코드를 통해 약속을 찾는다")
    @Test
    void 초대코드를_통해_약속을_찾는다() {
        //given
        InviteCode inviteCode = new InviteCode(123456L);
        Appointment appointment = new Appointment("수현의 약속", inviteCode, LocalDateTime.now().plusDays(1), 3L);
        Category category = new Category("스터디", CategoryColor._9A61D2, appointment);
        appointment.updateCategory(category);

        inviteCodeRepository.save(inviteCode);
        appointmentRepository.save(appointment);
        categoryRepository.save(category);

        //when
        Appointment actual = appointmentRepository.getByInviteCode(inviteCode.getCode());

        //then
        assertThat(actual).isEqualTo(appointment);

    }

    @DisplayName("존재하지 않는 초대코드로 약속을 조회하면 예외를 발생시킨다.")
    @Test
    void 존재하지_않는_초대코드를_약속을_조회하면_예외를_발생시킨다() {
        //given
        InviteCode inviteCode = new InviteCode(123456L);
        Appointment appointment = new Appointment("수현의 약속", inviteCode, LocalDateTime.now().plusDays(1), 3L);
        Category category = new Category("스터디", CategoryColor._9A61D2, appointment);
        appointment.updateCategory(category);

        inviteCodeRepository.save(inviteCode);
        appointmentRepository.save(appointment);
        categoryRepository.save(category);

        //when & then
        Assertions.assertThatThrownBy(() -> {
            appointmentRepository.getByInviteCode(111111L);
        }).isInstanceOf(NoExistAppointmentException.class);
    }

    @DisplayName("존재하는 초대 코드라면 참을 반환한다.")
    @Test
    void 존재하는_초대_코드라면_참을_반환한다() {
        //given
        InviteCode inviteCode = new InviteCode(123456L);
        Appointment appointment = new Appointment("수현의 약속", inviteCode, LocalDateTime.now().plusDays(1), 3L);

        inviteCodeRepository.save(inviteCode);
        appointmentRepository.save(appointment);

        //when, then
        assertThat(inviteCodeRepository.existsByCode(appointment.getInviteCode().getCode())).isEqualTo(true);
    }

    @DisplayName("아이디를 통해 약속을 반환한다.")
    @Test
    void 아이디를_통해_약속을_반환한다() {
        //given
        InviteCode inviteCode = new InviteCode(123456L);
        Appointment appointment = new Appointment("수현의 약속", inviteCode, LocalDateTime.now().plusDays(1), 3L);

        inviteCodeRepository.save(inviteCode);
        appointmentRepository.save(appointment);
        Long appointmentId = appointment.getId();

        //when
        Appointment actual = appointmentRepository.getById(appointmentId);

        //then
        assertThat(actual).isEqualTo(appointment);
    }

    @DisplayName("아이디로 없는 약속을 찾은 경우엔 예외를 발생시킨다.")
    @Test
    void 아이디로_없는_약속을_찾은_경우엔_예외를_발생시킨다() {
        //given
        Long appointmentId = 1L;

        //when,then
        assertThatThrownBy(() -> appointmentRepository.getById(appointmentId))
                .isInstanceOf(NoExistAppointmentException.class);
    }

    @DisplayName("카테고리 이름을 통해 로그인 된 유저가 속한 약속들을 조회하고 페이징한다")
    @Test
    void 카테고리_이름을_통해_로그인된_유저가_속한_약속들을_조회하고_페이징한다() {
        //given
        InviteCode inviteCode1 = new InviteCode(123456L);
        InviteCode inviteCode2 = new InviteCode(654321L);
        Member member = new Member(수현_아이디, 수현_프로필_이미지, 수현_이름, SocialType.KAKAO);
        Appointment appointment1 = new Appointment("수현의 약속", inviteCode1, LocalDateTime.now().plusDays(1), 3L);
        Appointment appointment2 = new Appointment("만성의 약속", inviteCode2, LocalDateTime.now().plusDays(1), 3L);
        Category category1 = new Category("스터디", CategoryColor._9A61D2, appointment1);
        Category category2 = new Category("스터디", CategoryColor._9A61D2, appointment2);
        AppointmentUser appointmentUser1 = new AppointmentUser(appointment1, member, MemberAuthority.ADMIN);
        AppointmentUser appointmentUser2 = new AppointmentUser(appointment2, member, MemberAuthority.ADMIN);
        appointment1.updateCategory(category1);
        appointment2.updateCategory(category2);
        PageRequest pageRequest = PageRequest.of(0, 1);

        memberRepository.save(member);
        inviteCodeRepository.save(inviteCode1);
        inviteCodeRepository.save(inviteCode2);
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentUserRepository.save(appointmentUser1);
        appointmentUserRepository.save(appointmentUser2);
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        //when
        Page<Appointment> actual = appointmentRepository.findByUserAndCategoryName(member, category1.getName(), pageRequest);

        //then
        assertThat(actual).hasSize(1);
    }
}