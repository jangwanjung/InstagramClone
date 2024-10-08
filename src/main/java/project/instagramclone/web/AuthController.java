package project.instagramclone.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.instagramclone.dto.JoinReqDto;
import project.instagramclone.service.UserService;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "auth/joinForm";
    }

    @PostMapping("/auth/join")
    public String join(JoinReqDto joinReqDto){
        userService.회원가입(joinReqDto);
        return "redirect:/auth/loginForm";
    }

}
