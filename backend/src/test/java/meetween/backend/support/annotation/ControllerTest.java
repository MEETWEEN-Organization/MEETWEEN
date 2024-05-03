package meetween.backend.support.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import meetween.backend.appointment.presentation.AppointmentController;
import meetween.backend.appointment.service.AppointmentService;
import meetween.backend.authentication.infrastructure.jwt.JwtTokenProvider;
import meetween.backend.authentication.presentataion.AuthArgumentResolver;
import meetween.backend.authentication.presentataion.BearerTokenExtractor;
import meetween.backend.authentication.service.AuthService;
import meetween.backend.member.presentation.MemberController;
import meetween.backend.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureRestDocs
@WebMvcTest({
        MemberController.class,
        AppointmentController.class
})
@ActiveProfiles("test")
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected AppointmentService appointmentService;

    @MockBean
    protected JwtTokenProvider jwtTokenProvider;

    @MockBean
    protected BearerTokenExtractor bearerTokenExtractor;

    @MockBean
    protected AuthArgumentResolver authArgumentResolver;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected AuthService authService;
}
