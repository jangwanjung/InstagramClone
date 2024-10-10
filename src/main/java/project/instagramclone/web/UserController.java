package project.instagramclone.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.instagramclone.config.auth.LoginUserAnnotation;
import project.instagramclone.config.auth.dto.LoginUser;
import project.instagramclone.service.UserService;
import project.instagramclone.web.dto.UserProfileRespDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, @LoginUserAnnotation LoginUser loginUser, Model model) {
        UserProfileRespDto userProfileRespDto = userService.회원프로필(id,loginUser);
        model.addAttribute("respDto", userProfileRespDto);
        return "user/profile";
    }

}
