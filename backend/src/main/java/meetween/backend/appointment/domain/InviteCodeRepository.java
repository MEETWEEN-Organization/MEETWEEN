package meetween.backend.appointment.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteCodeRepository extends JpaRepository<InviteCode, Long> {

    boolean existsByCode(Long inviteCode);
}
