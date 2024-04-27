package meetween.backend.member.domain;

import meetween.backend.member.exception.NoExistMemberException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsBySocialLoginId(final String socialLoginId);
    Optional<Member> findBySocialLoginId(final String socialLoginId);

    default Member getById(final Long id) {
        return findById(id).
                orElseThrow(NoExistMemberException::new);
    }
}
