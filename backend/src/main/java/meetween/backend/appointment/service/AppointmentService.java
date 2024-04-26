package meetween.backend.appointment.service;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.domain.AppointmentUser;
import meetween.backend.appointment.domain.AppointmentUserRepository;
import meetween.backend.appointment.dto.request.AppointmentCreateRequest;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.category.domain.Category;
import meetween.backend.category.domain.CategoryColor;
import meetween.backend.category.domain.CategoryRepository;
import meetween.backend.user.domain.User;
import meetween.backend.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentUserRepository appointmentUserRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public AppointmentService(final AppointmentRepository appointmentRepository, final AppointmentUserRepository appointmentUserRepository, final CategoryRepository categoryRepository, final UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentUserRepository = appointmentUserRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AppointmentResponse save(final Long memberId, final AppointmentCreateRequest request) {
        User user = userRepository.getById(memberId);
        Category category = categoryRepository.save(new Category(request.getCategoryName(), CategoryColor.getCategoryColor(request.getCategoryColor())));

        Appointment appointment = appointmentRepository.save(request.toEntity(category));

        appointmentUserRepository.save(new AppointmentUser(appointment, user));

        return new AppointmentResponse(appointment);
    }
}
