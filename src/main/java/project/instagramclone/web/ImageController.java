package project.instagramclone.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project.instagramclone.config.auth.LoginUserAnnotation;
import project.instagramclone.config.auth.PrincipalDetails;
import project.instagramclone.dto.LoginUser;

@Controller
public class ImageController {

    @GetMapping({"","/","/image/feed"})
    public String feed(@LoginUserAnnotation LoginUser loginUser) {
        System.out.println(loginUser);
        return "image/feed";
    }
}
