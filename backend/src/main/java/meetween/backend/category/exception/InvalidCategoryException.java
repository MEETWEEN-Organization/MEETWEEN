package meetween.backend.category.exception;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(final String message) {
        super(message);
    }

    public InvalidCategoryException() {
        super("잘못된 카테고리 입니다.");
    }
}
