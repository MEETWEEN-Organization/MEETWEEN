package meetween.backend.user.service;

import meetween.backend.user.domain.User;
import meetween.backend.user.domain.UserRepository;
import meetween.backend.user.exception.NoExistUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository memberRepository;

    public UserService(final UserRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public User save(final User member) {
        return memberRepository.save(member);
    }

    public User findByEmail(final String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(NoExistUserException::new);
    }

    public boolean existsByEmail(final String email) {
        return memberRepository.existsByEmail(email);
    }
}