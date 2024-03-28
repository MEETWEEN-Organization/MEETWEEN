package meetween.backend.user.dto;

public class OAuthMember {
    private final String email;
    private final String displayName;
    private final String profileImageUri;

    public OAuthMember(final String email, final String displayName, final String profileImageUri) {
        this.email = email;
        this.displayName = displayName;
        this.profileImageUri = profileImageUri;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProfileImageUri() {
        return profileImageUri;
    }
}
