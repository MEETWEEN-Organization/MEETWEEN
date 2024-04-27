package meetween.backend.member.exception;

public class NoExistMemberException extends RuntimeException {
    public NoExistMemberException() {
        super("존재하지 않는 회원입니다.");
    }

    public NoExistMemberException(final String message) {
        super(message);
    }
}
