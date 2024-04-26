package meetween.backend.support.fixture.common;

import meetween.backend.category.domain.Category;
import meetween.backend.category.domain.CategoryColor;

public class CategoryFixtures {
    public static Category 스터디_카테고리_생성() {
        return new Category(
                "스터디",
                CategoryColor._9A61D2
        );
    }
}
