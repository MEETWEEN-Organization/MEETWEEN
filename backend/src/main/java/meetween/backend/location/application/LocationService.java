package meetween.backend.location.application;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.domain.AppointmentRepository;
import meetween.backend.appointment.domain.AppointmentUserRepository;
import meetween.backend.appointment.dto.response.AppointmentResponse;
import meetween.backend.appointment.exception.NoExistAppointmentUserException;
import meetween.backend.location.domain.Location;
import meetween.backend.location.domain.LocationRepository;
import meetween.backend.location.domain.LocationType;
import meetween.backend.location.dto.request.LocationAddRequest;
import meetween.backend.location.exception.NotOnlyOneLocationException;
import meetween.backend.member.domain.Member;
import meetween.backend.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class LocationService {

    private final AppointmentRepository appointmentRepository;
    private final LocationRepository locationRepository;
    private final AppointmentUserRepository appointmentUserRepository;
    private final MemberRepository memberRepository;

    public LocationService(final AppointmentRepository appointmentRepository, final LocationRepository locationRepository, final AppointmentUserRepository appointmentUserRepository, final MemberRepository memberRepository) {
        this.appointmentRepository = appointmentRepository;
        this.locationRepository = locationRepository;
        this.appointmentUserRepository = appointmentUserRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public AppointmentResponse addLocation(final Long memberId, final Long appointmentId, final LocationAddRequest request) {
        Appointment appointment = appointmentRepository.getById(appointmentId);
        Member member = memberRepository.getById(memberId);
        validateIsIncludedMember(member, appointment);

        Location location = new Location(appointment, request.getLatitude(), request.getLongitude(), LocationType.PROPOSED);
        locationRepository.save(location);

        return new AppointmentResponse(appointment, location);
    }

    @Transactional
    public AppointmentResponse changeMain(final Long memberId, final Long appointmentId, final Long locationId) {
        Appointment appointment = appointmentRepository.getById(appointmentId);
        Member member = memberRepository.getById(memberId);
        validateIsIncludedMember(member, appointment);

        locationRepository.getChoicedLocationByAppointment(appointment).changeLocationType();
        Location location = locationRepository.getById(locationId);
        location.changeLocationType();
        validateIsOnlyOneMainLocation(appointment);

        return new AppointmentResponse(appointment, location);
    }

    private void validateIsIncludedMember(final Member member, final Appointment appointment) {
        if (!appointmentUserRepository.existsByAppointmentAndMember(appointment, member)) {
            throw new NoExistAppointmentUserException();
        }
    }

    private void validateIsOnlyOneMainLocation(Appointment appointment) {
        if (locationRepository.findChoicedLocationByAppointment(appointment).size() != 1) {
            throw new NotOnlyOneLocationException();
        }
    }
}
