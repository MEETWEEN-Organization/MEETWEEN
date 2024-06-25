package meetween.backend.appointment.exception;

public class NotAdminMemberException extends RuntimeException {
    public NotAdminMemberException(final String message) {
        super(message);
    }

    public NotAdminMemberException() {
        super("해당 멤버는 ADMIN이 아닙니다.");
    }
}
