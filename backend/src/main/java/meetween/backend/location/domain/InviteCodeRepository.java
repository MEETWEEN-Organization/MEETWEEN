package meetween.backend.location.domain;

import meetween.backend.invitecode.domain.InviteCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteCodeRepository extends JpaRepository<InviteCode, Long> {

    boolean existsByCode(Long inviteCode);
}
