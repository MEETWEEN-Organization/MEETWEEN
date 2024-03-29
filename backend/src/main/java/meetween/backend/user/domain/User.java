package meetween.backend.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import meetween.backend.global.entity.BaseEntity;
import meetween.backend.user.exception.InvalidUserException;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    private static final int MAX_DISPLAY_NAME_LENGTH = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String socialLoginId;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;

    @Column(nullable = false)
    private String displayName;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    protected User() {}

    public User(final String socialLoginId, final String profileImageUrl, final String displayName, final SocialType socialType) {
        validateDisplayName(socialLoginId);
        this.socialLoginId = socialLoginId;
        this.profileImageUrl = profileImageUrl;
        this.displayName = displayName;
        this.socialType = socialType;
    }

    private void validateDisplayName(final String displayName) {
        if(displayName.isEmpty() || displayName.length() > MAX_DISPLAY_NAME_LENGTH) {
            throw new InvalidUserException("이름 형식이 올바르지 않습니다.");

        }
    }

    public Long getId() {
        return id;
    }
}
