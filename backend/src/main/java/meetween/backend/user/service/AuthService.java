package meetween.backend.user.service;

import meetween.backend.user.client.OAuthClient;
import meetween.backend.user.domain.SocialType;
import meetween.backend.user.domain.User;
import meetween.backend.user.dto.OAuthMember;
import meetween.backend.user.dto.TokenResponse;
import meetween.backend.user.oauth.endpoint.ProviderProperties;
import meetween.backend.user.provider.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {
    private final ProviderProperties providerProperties;
    private final OAuthClient oAuthClient;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(final ProviderProperties providerProperties, final OAuthClient oAuthClient,
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
        String email = oAuthMember.getEmail();

        if (!userService.existsByEmail(email)) {
            userService.save(generateUserBy(oAuthMember));
        }

        User foundMember = userService.findByEmail(email);
        String accessToken = jwtTokenProvider.createToken(String.valueOf(foundMember.getId()));
        return new TokenResponse(accessToken);
    }

    private User generateUserBy(final OAuthMember oAuthMember) {
        return new User(oAuthMember.getEmail(),
                oAuthMember.getProfileImageUri(),
                oAuthMember.getDisplayName(),
                SocialType.KAKAO);
    }


}
