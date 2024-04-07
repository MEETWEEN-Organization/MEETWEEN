package meetween.backend.user.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsBySocialLoginId(final String socialLoginId);
    Optional<User> findBySocialLoginId(final String socialLoginId);
}
