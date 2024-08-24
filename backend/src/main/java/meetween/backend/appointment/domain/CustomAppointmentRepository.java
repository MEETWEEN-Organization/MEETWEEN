package meetween.backend.appointment.domain;

import meetween.backend.appointment.domain.Appointment;
import meetween.backend.appointment.dto.response.AppointmentPageDto;
import meetween.backend.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAppointmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomAppointmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AppointmentPageDto> paginationWithCoveringIndex(Member member, String categoryName, PageRequest pageRequest) {
        String query =
                "SELECT a.id, c.id as categoryId, a.title, a.appointment_date_time, ic.code as inviteCode, a.member_count " +
                        "FROM appointment as a " +
                        "JOIN category as c ON a.id = c.appointment_id " +
                        "JOIN invite_code as ic ON ic.id = a.invite_code_id " +
                        "JOIN (SELECT a.id " +
                        "      FROM appointment as a" +
                        "      JOIN appointment_and_user as au ON a.id = au.apppointment_id " +
                        "      JOIN category as c ON a.id = c.appointment_id " +
                        "      WHERE au.member_id = ? " +
                        "      AND c.name = ? " +
                        "      LIMIT ? OFFSET ?) " +
                        "      as temp_appointment on temp_appointment.id = a.id";

        return jdbcTemplate
                .query(query, new BeanPropertyRowMapper<>(AppointmentPageDto.class),
                        member.getId(),
                        categoryName,
                        pageRequest.getPageSize(),
                        pageRequest.getOffset()
                        );
    }

    public Integer getTotalPageCount(Member member, String categoryName, PageRequest pageRequest) {
        String countQuery =
                "SELECT COUNT(DISTINCT a.id)" +
                        "FROM appointment as a " +
                        "JOIN appointment_and_user as au ON a.id = au.apppointment_id " +
                        "JOIN category as c ON a.id = c.appointment_id " +
                        "WHERE au.member_id = ? " +
                        "AND c.name = ?";

        Integer totalCount = jdbcTemplate.queryForObject(countQuery, Integer.class, member.getId(), categoryName);

        return (totalCount + pageRequest.getPageSize() - 1) / pageRequest.getPageSize();
    }
}
