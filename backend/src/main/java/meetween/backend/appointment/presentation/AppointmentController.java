package meetween.backend.appointment.presentation;


import jakarta.validation.Valid;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.service.AppointmentService;
import meetween.backend.authentication.dto.LoginMember;
import meetween.backend.authentication.presentataion.AuthPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> save(@AuthPrincipal LoginMember loginMember,
                                                    @Valid @RequestBody final AppointmentCreateRequest request) {
        AppointmentResponse appointmentResponse = appointmentService.save(loginMember.getId(), request);
        return ResponseEntity.created(URI.create("/appointment/" + appointmentResponse.getId())).body(appointmentResponse);
    }
}