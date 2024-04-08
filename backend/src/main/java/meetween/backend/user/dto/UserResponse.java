package meetween.backend.user.dto;


import meetween.backend.user.domain.User;

public class UserResponse {
    private Long id;
    private String socialLoginId;;
    private String profileImageUrl;
    private String displayName;

    private UserResponse() {}

    public UserResponse(final Long id, final String socialLoginId, final String profileImageUrl, final String displayName) {
        this.id = id;
        this.socialLoginId = socialLoginId;
        this.profileImageUrl = profileImageUrl;
        this.displayName = displayName;
    }

    public UserResponse(final User user) {
        this(user.getId(), user.getSocialLoginId(), user.getProfileImageUrl(), user.getDisplayName());
    }

    public Long getId() {
        return id;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getDisplayName() {
        return displayName;
    }
}
