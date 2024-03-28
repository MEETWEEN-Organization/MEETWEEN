package meetween.backend.user.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(final String email);
    Optional<User> findByEmail(final String email);
}
