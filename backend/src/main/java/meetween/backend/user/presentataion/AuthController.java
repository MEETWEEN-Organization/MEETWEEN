package meetween.backend.user.presentataion;

import meetween.backend.user.dto.OAuthUriResponse;
import meetween.backend.user.dto.TokenRequest;
import meetween.backend.user.dto.TokenResponse;
import meetween.backend.user.service.AuthService;
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
    public ResponseEntity<OAuthUriResponse> generateUri(@PathVariable final String provider,
                                                         @RequestParam final String redirectUri) {
        return ResponseEntity.ok(new OAuthUriResponse(authService.getSocialLink()));
    }

    // 로그인 성공 후 발급받은 code 를 백엔드에 전달한다. (즉, 프론트엔드 페이지에서 얻은 인가 code 를 전달받음)
    // 이 전달된 code 를 전달받고 로그인에 필요한 토큰을 받급해준다.
    @PostMapping("/{provider}/token")
    public ResponseEntity<TokenResponse> generateToken(@PathVariable final String provider,
                                                       @RequestBody final TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.generateTokenWithCode(tokenRequest.getCode());
        return ResponseEntity.ok(tokenResponse);
    }
}
