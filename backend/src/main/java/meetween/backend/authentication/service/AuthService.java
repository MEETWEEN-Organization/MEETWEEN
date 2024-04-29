package meetween.backend.authentication.service;

import meetween.backend.authentication.domain.OAuthMember;
import meetween.backend.authentication.infrastructure.client.OAuthClient;
import meetween.backend.member.domain.SocialType;
import meetween.backend.member.domain.Member;
import meetween.backend.authentication.domain.KakaoOAuthMember;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.authentication.infrastructure.uri.OAuthUriProvider;
import meetween.backend.authentication.infrastructure.jwt.JwtTokenProvider;
import meetween.backend.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {
    private final OAuthUriProvider oAuthUriProvider;
    private final OAuthClient oAuthClient;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(final OAuthUriProvider oAuthUriProvider, final OAuthClient oAuthClient,
                       final MemberService memberService, final JwtTokenProvider jwtTokenProvider) {
        this.oAuthUriProvider = oAuthUriProvider;
        this.oAuthClient = oAuthClient;
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String getSocialLink() {
        return oAuthUriProvider.generate();
    }

    @Transactional
    public TokenResponse generateTokenWithCode(final String code) {
        final OAuthMember oAuthMember = oAuthClient.getOAuthMember(code);
        final Member foundUser = findOrCreateUser(oAuthMember);
        final String accessToken = jwtTokenProvider.createToken(String.valueOf(foundUser.getId()));  // pk 를 payload 로 지정
        return new TokenResponse(accessToken);
    }

    private Member findOrCreateUser(OAuthMember oAuthMember) {
        String socialLoginId = oAuthMember.getSocialLoginId();

        if (!memberService.existsBySocialLoginId(socialLoginId)) {
            memberService.save(generateMemberBy(oAuthMember));
        }
        final Member foundMember = memberService.findBySocialLoginId(socialLoginId);
        return foundMember;
    }

    private Member generateMemberBy(final OAuthMember oAuthMember) {
        return new Member(oAuthMember.getSocialLoginId(),
                oAuthMember.getImageUrl(),
                oAuthMember.getNickname(),
                SocialType.KAKAO);
    }
}
