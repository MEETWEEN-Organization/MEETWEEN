package meetween.backend.appointment.domain;

import meetween.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentUserRepository extends JpaRepository<AppointmentUser, Long> {
    List<AppointmentUser> findAllByMember(Member member);

}
