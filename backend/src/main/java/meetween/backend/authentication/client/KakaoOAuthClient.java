package meetween.backend.authentication.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import meetween.backend.authentication.domain.OAuthAccessToken;
import meetween.backend.authentication.dto.OAuthMember;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


// 카카오 소설 계정 사용자
@Component
public class KakaoOAuthClient implements OAuthClient {
    private static final String DELIMITER = "\\.";
    private final String grantType = "authorization_code";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String redirectUri;
    private final String clientId;
    private final String clientSecret;
    private final String tokenUri;
    private final String userUri;


    public KakaoOAuthClient(final RestTemplate restTemplate,
                            final ObjectMapper objectMapper,
                            @Value("${oauth.kakao.redirect_uri}") final String redirectUri,
                            @Value("${oauth.kakao.client_id}") final String clientId,
                            @Value("${oauth.kakao.client_secret}") final String clientSecret,
                            @Value("${oauth.kakao.token_uri}") final String tokenUri,
                            @Value("${oauth.kakao.user_uri}") final String userUri) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.redirectUri = redirectUri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenUri = tokenUri;
        this.userUri = userUri;
    }

    @Override
    public OAuthMember getOAuthMember(final String code) {
        final String accessToken = requestKakaoAccessToken(code);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);
        final HttpEntity<MultiValueMap<String, String>> userInfoRequestEntity = new HttpEntity<>(httpHeaders);

        final ResponseEntity<OAuthMember> oAuthMember = restTemplate.exchange(
                userUri,
                HttpMethod.GET,
                userInfoRequestEntity,
                OAuthMember.class
        );

        return oAuthMember.getBody();
    }

    private String requestKakaoAccessToken(String code) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(clientId, clientSecret);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", grantType);

        final HttpEntity<MultiValueMap<String, String>> accessTokenRequestEntity = new HttpEntity<>(params, httpHeaders);
        final ResponseEntity<OAuthAccessToken> accessToken = restTemplate.exchange(
                tokenUri,
                HttpMethod.POST,
                accessTokenRequestEntity,
                OAuthAccessToken.class
        );

        return Optional.ofNullable(accessToken.getBody())
                .orElseThrow(IllegalArgumentException::new)
                .getAccessToken();
    }

    private MultiValueMap<String, String> generateRequestParams(final String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");
        return params;
    }

    private String getPayloadFrom(final String jwtToken) {
        return jwtToken.split(DELIMITER)[1];
    }

    private String getDecodedJwtPayload(String payload) {
        return new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
    }
}
