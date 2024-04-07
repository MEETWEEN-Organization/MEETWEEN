package meetween.backend.authentication.presentataion;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import meetween.backend.authentication.exception.InvalidTokenException;
import org.springframework.http.HttpHeaders;

public class AuthExtractor {
    private static final String BEARER_TYPE = "Bearer ";

    public static String extract(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if(Objects.isNull(authorizationHeader)) {
            throw new EmptyAuthHeaderException();
        }

        validateAuthorizationFormat(authorizationHeader);

        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }

    private static void validateAuthorizationFormat(String authorizationHeader) {
        if(!authorizationHeader.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
            throw new InvalidTokenException();
        }
    }
}
