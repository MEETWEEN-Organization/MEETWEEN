package meetween.backend.member.application;

import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import meetween.backend.member.dto.MemberResponse;
import meetween.backend.member.exception.NoExistMemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse findById(final Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(NoExistMemberException::new);
        return new MemberResponse(member);
    }

    @Transactional
    public Member save(final Member member) {
        return memberRepository.save(member);
    }

    public Member findBySocialLoginId(final String socialLoginId) {
        return memberRepository.findBySocialLoginId(socialLoginId)
                .orElseThrow(NoExistMemberException::new);
    }

    public boolean existsBySocialLoginId(final String socialLoginId) {
        return memberRepository.existsBySocialLoginId(socialLoginId);
    }
}
