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

    public User findBySocialLoginId(final String socialLoginId) {
        return memberRepository.findBySocialLoginId(socialLoginId)
                .orElseThrow(NoExistUserException::new);
    }

    public boolean existsBySocialLoginId(final String socialLoginId) {
        return memberRepository.existsBySocialLoginId(socialLoginId);
    }
}