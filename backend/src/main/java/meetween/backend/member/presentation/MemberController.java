package meetween.backend.member.presentation;

import meetween.backend.authentication.dto.LoginMember;
import meetween.backend.authentication.presentataion.AuthPrincipal;
import meetween.backend.member.dto.MemberResponse;
import meetween.backend.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/about")
    public ResponseEntity<MemberResponse> about(@AuthPrincipal LoginMember loginMember) {
        MemberResponse memberResponse = memberService.findById(loginMember.getId());
        return ResponseEntity.ok(memberResponse);
    }
}
