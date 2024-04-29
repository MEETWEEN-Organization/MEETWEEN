package meetween.backend.authentication.infrastructure.uri;

@FunctionalInterface
public interface OAuthUriProvider {
    String generate();
}
