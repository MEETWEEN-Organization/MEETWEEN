package meetween.backend.authentication.infrastructure.uri;

public interface OAuthUriProvider {
    String generate();
    boolean isSame(String provider);
}
