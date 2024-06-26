package meetween.backend.location.presentation;

import jakarta.validation.Valid;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.authentication.dto.LoginMember;
import meetween.backend.authentication.presentataion.AuthPrincipal;
import meetween.backend.location.application.LocationService;
import meetween.backend.location.dto.request.LocationAddRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/location")
@RestController
public class LocationController {

    private final LocationService locationService;

    public LocationController(final LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> addLocation(@AuthPrincipal final LoginMember loginMember,
                                                           @PathVariable final Long appointmentId,
                                                           @Valid @RequestBody final LocationAddRequest request) {
        AppointmentResponse response = locationService.addLocation(loginMember.getId(), appointmentId, request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{appointmentId}/change-main/{locationId}")
    public ResponseEntity<AppointmentResponse> changeMain(@AuthPrincipal final LoginMember loginMember,
                                                          @PathVariable final Long appointmentId,
                                                          @PathVariable final Long locationId) {
        AppointmentResponse response = locationService.changeMain(loginMember.getId(), appointmentId, locationId);
        return ResponseEntity.ok().body(response);
    }
}
