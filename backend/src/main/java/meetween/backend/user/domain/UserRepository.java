package meetween.backend.user.domain;

import java.util.Optional;

import meetween.backend.user.exception.NoExistUserException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsBySocialLoginId(final String socialLoginId);
    Optional<User> findBySocialLoginId(final String socialLoginId);

    default User getById(final Long id) {
        return findById(id).
                orElseThrow(NoExistUserException::new);
    }
}
