package meetween.backend.authentication.presentataion;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import meetween.backend.authentication.exception.EmptyAuthHeaderException;
import meetween.backend.authentication.exception.InvalidTokenException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


public class BearerTokenExtractor {
    private static final String BEARER_TYPE = "Bearer ";

    public static String extractValidAccessToken(final HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        validateEmptyHeader(authorizationHeader);
        validateAuthorizationFormat(authorizationHeader);

        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }

    private static void validateAuthorizationFormat(final String authorizationHeader) {
        if(!authorizationHeader.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
            throw new InvalidTokenException();
        }
    }

    private static void validateEmptyHeader(String authorizationHeader) {
        if(Objects.isNull(authorizationHeader)) {
            throw new EmptyAuthHeaderException();
        }
    }
}