package meetween.backend.user.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import meetween.backend.user.domain.OAuthAccessToken;
import meetween.backend.user.dto.OAuthMember;
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
    private static final String KAKAO_REDIRECT_URI = "https://kauth.kakao.com/oauth/authorize";
    private static final String KAKAO_LOGIN_TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_USER_URI = "";
    private static final String DELIMITER = "\\.";
    private final String grant_type = "authorization_code";
    private final String clientId = "kakao_client_id";
    private final String clientSecret = "kakao_client_secret";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public KakaoOAuthClient(final RestTemplate restTemplate,
                            final ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public OAuthMember getOAuthMember(final String code) {
        final String accessToken = requestKakaoAccessToken(code);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);
        final HttpEntity<MultiValueMap<String, String>> userInfoRequestEntity = new HttpEntity<>(httpHeaders);

        final ResponseEntity<OAuthMember> oAuthMember = restTemplate.exchange(
                KAKAO_USER_URI,
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
        params.add("redirect_uri", KAKAO_REDIRECT_URI);
        params.add("grant_type", "authorization_code");

        final HttpEntity<MultiValueMap<String, String>> accessTokenRequestEntity = new HttpEntity<>(params, httpHeaders);
        final ResponseEntity<OAuthAccessToken> accessToken = restTemplate.exchange(
                KAKAO_LOGIN_TOKEN_URI,
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
        params.add("redirect_uri", KAKAO_REDIRECT_URI);
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
