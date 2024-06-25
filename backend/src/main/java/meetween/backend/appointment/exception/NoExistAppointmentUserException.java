package meetween.backend.appointment.exception;

public class NoExistAppointmentUserException extends RuntimeException {
    public NoExistAppointmentUserException(final String message) {
        super(message);
    }

    public NoExistAppointmentUserException() {
        super("존재하지 않는 약속-유저입니다");
    }
}
