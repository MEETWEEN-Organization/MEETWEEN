package meetween.backend.authentication.presentataion;

import meetween.backend.authentication.dto.OAuthUriResponse;
import meetween.backend.authentication.dto.TokenRequest;
import meetween.backend.authentication.dto.TokenResponse;
import meetween.backend.authentication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/{provider}/link")
    public ResponseEntity<OAuthUriResponse> generateUri(@PathVariable final String provider) {
        return ResponseEntity.ok(new OAuthUriResponse(authService.getSocialLink()));
    }

    // 로그인 성공 후 발급받은 code 를 백엔드에 전달한다. (즉, 프론트엔드 페이지에서 얻은 인가 code 를 전달받음)
    // 이 전달된 code 를 전달받고 로그인에 필요한 토큰을 프론트엔드에게 받급해준다. (프론트엔드는 이 리턴해준 토큰을 로컬 스토리지에 저장)
    @PostMapping("/{provider}/token")
    public ResponseEntity<TokenResponse> generateToken(@PathVariable final String provider,
                                                       @RequestBody final TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.generateTokenWithCode(tokenRequest.getCode());
        return ResponseEntity.ok(tokenResponse);
    }
}
