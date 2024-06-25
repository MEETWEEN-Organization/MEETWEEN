package meetween.backend.appointment.presentation;


import jakarta.validation.Valid;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.request.AppointmentParticipateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.appointment.application.AppointmentService;
import meetween.backend.appointment.application.AppointmentUserService;
import meetween.backend.authentication.dto.LoginMember;
import meetween.backend.authentication.presentataion.AuthPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentUserService appointmentUserService;

    public AppointmentController(final AppointmentService appointmentService, final AppointmentUserService appointmentUserService) {
        this.appointmentService = appointmentService;
        this.appointmentUserService = appointmentUserService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> save(@AuthPrincipal final LoginMember loginMember,
                                                    @Valid @RequestBody final AppointmentCreateRequest request) {
        AppointmentResponse response = appointmentService.save(loginMember.getId(), request);
        return ResponseEntity.created(URI.create("/appointment/" + response.getId())).body(response);
    }

    @PostMapping("participate")
    public ResponseEntity<AppointmentResponse> participate(@AuthPrincipal final LoginMember loginMember,
                                                           @Valid @RequestBody final AppointmentParticipateRequest request) {
        AppointmentResponse response = appointmentService.participate(loginMember.getId(), request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("my")
    public ResponseEntity<IntegratedAppointmentResponses> findMyAllAppointments(@AuthPrincipal final LoginMember loginMember) {
        IntegratedAppointmentResponses response = appointmentService.findAll(loginMember.getId());
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{appointmentId}/{targetMemberId}/authority")
    public ResponseEntity<Void> updateAuthority(@AuthPrincipal final LoginMember loginMember,
                                                @PathVariable final Long appointmentId,
                                                @PathVariable final Long targetMemberId) {
        appointmentUserService.updateAuthority(appointmentId, loginMember.getId(), targetMemberId);
        return ResponseEntity.noContent().build();
    }
}