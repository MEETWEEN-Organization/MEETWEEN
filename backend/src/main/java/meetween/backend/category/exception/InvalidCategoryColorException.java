package meetween.backend.category.exception;

public class InvalidCategoryColorException extends RuntimeException {
    public InvalidCategoryColorException(final String message) {
        super(message);
    }

    public InvalidCategoryColorException() {
        super("잘못된 카테고리 색상 입니다.");
    }
}
