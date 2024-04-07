package meetween.backend.authentication.service;

import meetween.backend.authentication.client.OAuthClient;
import meetween.backend.user.domain.SocialType;
import meetween.backend.user.domain.User;
import meetween.backend.authentication.dto.OAuthMember;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.authentication.properties.OAuthProviderProperties;
import meetween.backend.authentication.provider.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {
    private final OAuthProviderProperties providerProperties;
    private final OAuthClient oAuthClient;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(final OAuthProviderProperties providerProperties, final OAuthClient oAuthClient,
                       final UserService userService, final JwtTokenProvider jwtTokenProvider) {
        this.providerProperties = providerProperties;
        this.oAuthClient = oAuthClient;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String getSocialLink() {
        return providerProperties.generate();
    }

    @Transactional
    public TokenResponse generateTokenWithCode(final String code) {
        OAuthMember oAuthMember = oAuthClient.getOAuthMember(code);
        User foundUser = findOrCreateUser(oAuthMember);
        String accessToken = jwtTokenProvider.createToken(String.valueOf(foundUser.getId()));  // pk 를 payload 로 지정
        return new TokenResponse(accessToken);
    }

    private User findOrCreateUser(OAuthMember oAuthMember) {
        String socialLoginId = oAuthMember.getSocialLoginId();

        if (!userService.existsBySocialLoginId(socialLoginId)) {
            userService.save(generateUserBy(oAuthMember));
        }
        User foundUser = userService.findBySocialLoginId(socialLoginId);
        return foundUser;
    }

    private User generateUserBy(final OAuthMember oAuthMember) {
        return new User(oAuthMember.getSocialLoginId(),
                oAuthMember.getImageUrl(),
                oAuthMember.getNickname(),
                SocialType.KAKAO);
    }
}
