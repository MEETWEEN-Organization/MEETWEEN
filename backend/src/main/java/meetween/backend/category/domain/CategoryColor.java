package meetween.backend.category.domain;

import meetween.backend.category.exception.InvalidCategoryColorException;

public enum CategoryColor {
    _B33434("#B33434"),
    _C47243("#C47243"),
    _DEA12A("#DEA12A"),
    _90B04A("#90B04A"),
    _488BBB("#488BBB"),
    _5B59B3("#5B59B3"),
    _9A61D2("#9A61D2"),
    _C161AC("#C161AC");

    private final String colorCode;

    private CategoryColor(String colorCode) {
        this.colorCode = colorCode;
    }

    public static CategoryColor getCategoryColor(String colorCode) {
        for (CategoryColor categoryColor : CategoryColor.values()) {
            if (categoryColor.colorCode.equals(colorCode)) {
                return categoryColor;
            }
        }
        throw new InvalidCategoryColorException();
    }
}
