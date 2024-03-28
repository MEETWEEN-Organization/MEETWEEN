package meetween.backend.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import meetween.backend.global.entity.BaseEntity;
import meetween.backend.user.exception.InvalidUserException;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._-]+@[a-z]+[.]+[a-z]{2,3}$");
    private static final int MAX_DISPLAY_NAME_LENGTH = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    protected User() {}

    public User(final String email, final String profileImageUrl, final String displayName, final SocialType socialType) {
        validateEmail(email);
        validateDisplayName(displayName);
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.displayName = displayName;
        this.socialType = socialType;
    }

    private void validateEmail(final String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if(!matcher.matches()) {
            throw new InvalidUserException("이메일 형식이 올바르지 않습니다.");
        }
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
