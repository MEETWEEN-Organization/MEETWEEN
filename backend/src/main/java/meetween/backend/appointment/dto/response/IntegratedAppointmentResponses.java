package meetween.backend.appointment.dto.response;

import java.util.List;

public class IntegratedAppointmentResponses {

    private final List<AppointmentResponse> appointmentResponses;

    private final int totalPages;

    public IntegratedAppointmentResponses(final List<AppointmentResponse> appointmentResponses, final int totalPages) {
        this.appointmentResponses = appointmentResponses;
        this.totalPages = totalPages;
    }

    public List<AppointmentResponse> getAppointmentResponses() {
        return appointmentResponses;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
