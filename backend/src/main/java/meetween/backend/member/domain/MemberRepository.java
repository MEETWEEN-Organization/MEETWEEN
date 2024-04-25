package meetween.backend.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsBySocialLoginId(final String socialLoginId);
    Optional<Member> findBySocialLoginId(final String socialLoginId);
}
