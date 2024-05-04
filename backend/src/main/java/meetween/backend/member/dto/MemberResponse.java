package meetween.backend.member.dto;


import meetween.backend.member.domain.Member;

public class MemberResponse {
    private Long id;
    private String socialLoginId;
    private String profileImageUrl;
    private String displayName;

    private MemberResponse() {}

    public MemberResponse(final Long id, final String socialLoginId, final String profileImageUrl, final String displayName) {
        this.id = id;
        this.socialLoginId = socialLoginId;
        this.profileImageUrl = profileImageUrl;
        this.displayName = displayName;
    }

    public MemberResponse(final Member member) {
        this(member.getId(), member.getSocialLoginId(), member.getProfileImageUrl(), member.getDisplayName());
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
