package meetween.backend.category.application;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.dto.response.IntegratedAppointmentResponses;
import meetween.backend.location.domain.Location;
import meetween.backend.location.domain.LocationRepository;
import meetween.backend.location.exception.NoExistLocationException;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final AppointmentRepository appointmentRepository;
    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    public CategoryService(final AppointmentRepository appointmentRepository, final MemberRepository memberRepository, final LocationRepository locationRepository) {
        this.appointmentRepository = appointmentRepository;
        this.memberRepository = memberRepository;
        this.locationRepository = locationRepository;
    }

    public IntegratedAppointmentResponses findByCategory(Long memberId, String categoryName, PageRequest pageRequest) {
        Member member = memberRepository.getById(memberId);
        Page<Appointment> appointmentPage = appointmentRepository.findByUserAndCategoryName(member, categoryName, pageRequest);
        List<Location> choicedLocations = locationRepository.findAllChoicedLocations();

        return new IntegratedAppointmentResponses(appointmentPage.stream()
                .map(appointment -> {
                    Location choicedLocation = choicedLocations.stream()
                            .filter(location -> location.getAppointment().equals(appointment))
                            .findFirst().orElseThrow(NoExistLocationException::new);
                    return new AppointmentResponse(appointment, choicedLocation);
                })
                .collect(Collectors.toList()), appointmentPage.getTotalPages());
    }
}
