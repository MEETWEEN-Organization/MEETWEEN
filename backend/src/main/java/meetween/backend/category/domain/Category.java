package meetween.backend.category.domain;

import jakarta.persistence.*;
import meetween.backend.category.exception.InvalidCategoryException;
import meetween.backend.global.entity.BaseEntity;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    private static final int MAX_NAME_LENGTH = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category_color", nullable = false)
    private CategoryColor categoryColor;

    protected Category() {}

    public Category(final String name, final CategoryColor categoryColor) {
        validateNameLength(name);
        this.name = name;
        this.categoryColor = categoryColor;
    }

    private void validateNameLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new InvalidCategoryException(String.format("카테고리 이름의 길이는 %d을 초과할 수 없습니다.", MAX_NAME_LENGTH));
        }
    }
}
