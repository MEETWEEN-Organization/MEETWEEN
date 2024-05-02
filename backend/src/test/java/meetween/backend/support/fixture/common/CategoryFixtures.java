package meetween.backend.support.fixture.common;

import meetween.backend.category.domain.Category;
import meetween.backend.category.domain.CategoryColor;

import static meetween.backend.support.fixture.common.AppointmentFixtures.수현_약속;


public class CategoryFixtures {

    /* 스터디 */
    public static final String 스터디_카테고리_제목 = "스터디";
    public static final CategoryColor 스터디_카테고리_컬러 = CategoryColor._9A61D2;
    public static final String 스터디_카테고리_컬러_문자 = "#9A61D2";

    public static Category 수현_약속_스터디_카테고리() {
        return new Category(스터디_카테고리_제목, 스터디_카테고리_컬러,수현_약속());
    }
}
