package meetween.backend.appointment.dto.response;

import java.util.List;

public class IntegratedAppointmentResponses {

    private final List<AppointmentResponse> appointmentResponses;

    public IntegratedAppointmentResponses(final List<AppointmentResponse> appointmentResponses) {
        this.appointmentResponses = appointmentResponses;
    }

    public List<AppointmentResponse> getAppointmentResponses() {
        return appointmentResponses;
    }
}
