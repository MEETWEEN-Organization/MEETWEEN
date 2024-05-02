package meetween.backend.appointment.exception;

public class NoExistAppointmentException extends RuntimeException {
    public NoExistAppointmentException(final String message) {
        super(message);
    }

    public NoExistAppointmentException() {
        super("존재하지 않는 약속입니다");
    }
}
