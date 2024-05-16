package meetween.backend.category.presentation;

import jakarta.validation.Valid;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.appointment.application.AppointmentService;
import meetween.backend.authentication.dto.LoginMember;
import meetween.backend.authentication.presentataion.AuthPrincipal;
import meetween.backend.category.application.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/category")
@RestController
public class CategoryController {

    public final CategoryService categoryService;
    public final AppointmentService appointmentService;

    public CategoryController(final CategoryService categoryService, final AppointmentService appointmentService) {
        this.categoryService = categoryService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<IntegratedAppointmentResponses> findAppointments(@AuthPrincipal LoginMember loginMember,
                                                                           @Valid @RequestParam final String categoryName) {
        IntegratedAppointmentResponses response = categoryService.findByCategory(loginMember.getId(), categoryName);
        return ResponseEntity.ok().body(response);
    }
}
