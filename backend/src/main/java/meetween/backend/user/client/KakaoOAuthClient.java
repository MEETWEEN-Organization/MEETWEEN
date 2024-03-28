package meetween.backend.user.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import meetween.backend.user.dto.KakaoTokenResponse;
import meetween.backend.user.dto.OAuthMember;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


// 카카오 소설 계정 사용자
@Component
public class KakaoOAuthClient implements OAuthClient {
    private static final String KAKAO_REDIRECT_URI = "https://kauth.kakao.com/oauth/authorize";
    private static final String KAKAO_LOGIN_TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    private static final String DELIMITER = "\\.";
    private final String grant_type = "authorization_code";
    private final String clientId;
    private final String clientSecret;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public KakaoOAuthClient(@Value("${}") final String clientId,
                            @Value("${}") final String clientSecret,
                            final RestTemplate restTemplate,
                            final ObjectMapper objectMapper) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public OAuthMember getOAuthMember(final String code) {
        try {
            KakaoTokenResponse kakaoTokenResponse = requestKakaoToken(code);
            String payload = getPayloadFrom(kakaoTokenResponse.getIdToken());
            String decodedPayload = getDecodedJwtPayload(payload);
            return generateOAuthUserBy(decodedPayload);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

    private KakaoTokenResponse requestKakaoToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = generateRequestParams(code);

        final HttpEntity<MultiValueMap<String, String>> accessTokenRequestEntity = new HttpEntity<>(params, headers);
        return restTemplate.postForEntity(KAKAO_LOGIN_TOKEN_URI, accessTokenRequestEntity, KakaoTokenResponse.class).getBody();
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


    private OAuthMember generateOAuthUserBy(final String decodedIdToken) throws JsonProcessingException {
        Map<String, String> userInfo = objectMapper.readValue(decodedIdToken, HashMap.class);
        String email = userInfo.get("email");
        String displayName = userInfo.get("name");
        String profileImageUri = userInfo.get("picture");
        return new OAuthMember(email, displayName, profileImageUri);
    }
}
