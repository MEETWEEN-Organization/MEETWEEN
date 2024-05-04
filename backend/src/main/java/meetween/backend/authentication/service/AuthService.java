package meetween.backend.authentication.service;

import meetween.backend.authentication.domain.OAuthProvider;
import meetween.backend.authentication.domain.oauthmember.OAuthMember;
import meetween.backend.authentication.domain.client.OAuthClient;
import meetween.backend.authentication.domain.token.InMemoryRefreshTokenRepository;
import meetween.backend.authentication.domain.token.MemberToken;
import meetween.backend.authentication.domain.token.RefreshTokenRepository;
import meetween.backend.authentication.dto.RenewalAccessTokenRequest;
import meetween.backend.authentication.dto.RenewalAccessTokenResponse;
import meetween.backend.authentication.exception.InvalidTokenException;
import meetween.backend.member.domain.SocialType;
import meetween.backend.member.domain.Member;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.authentication.infrastructure.uri.OAuthUriProvider;
import meetween.backend.authentication.infrastructure.jwt.JwtTokenProvider;
import meetween.backend.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {
    private final OAuthProvider oAuthProvider;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(
            final OAuthProvider oAuthProvider,
            final MemberService memberService,
            final JwtTokenProvider jwtTokenProvider) {
        this.oAuthProvider = oAuthProvider;
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String getSocialLink(String provider) {
        final OAuthUriProvider oAuthUriProvider = oAuthProvider.getOAuthUriProvider(provider);
        return oAuthUriProvider.generate();
    }

    @Transactional
    public MemberToken generateTokenWithCode(final String code, final String provider) {
        final OAuthClient oAuthClient = oAuthProvider.getOauthClient(provider);
        final OAuthMember oAuthMember = oAuthClient.getOAuthMember(code);
        final Member foundUser = findOrCreateUser(oAuthMember);
        final MemberToken memberToken = jwtTokenProvider.generateMemberToken(foundUser.getId());
        return memberToken;
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


    public RenewalAccessTokenResponse generateRenewalAccessToken(final RenewalAccessTokenRequest renewalAccessTokenRequest) {
        String refreshToken = renewalAccessTokenRequest.getRefreshToken();
        String renewalAccessToken = jwtTokenProvider.generateRenewalAccessToken(refreshToken);
        return new RenewalAccessTokenResponse(renewalAccessToken);
    }
}
