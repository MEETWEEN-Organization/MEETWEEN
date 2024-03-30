package meetween.backend.appointment.exception;

public class InvalidAppointmentException extends RuntimeException {
    public InvalidAppointmentException(final String message) {
        super(message);
    }

    public InvalidAppointmentException() {
        super("잘못된 약속입니다");
    }
}
