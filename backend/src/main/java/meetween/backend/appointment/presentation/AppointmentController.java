package meetween.backend.appointment.presentation;


import jakarta.validation.Valid;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.service.AppointmentService;
import meetween.backend.authentication.dto.LoginUser;
import meetween.backend.authentication.presentataion.AuthPrincipal;
import meetween.backend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(final AppointmentService appointmentService, final UserService userService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> save(@AuthPrincipal LoginUser loginUser,
                                                    @Valid @RequestBody final AppointmentCreateRequest request) {
        AppointmentResponse appointmentResponse = appointmentService.save(loginUser.getId(), request);
        return ResponseEntity.created(URI.create("/appointment/" + appointmentResponse.getId())).body(appointmentResponse);
    }
}
