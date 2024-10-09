package project.instagramclone.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project.instagramclone.config.auth.PrincipalDetails;
import project.instagramclone.dto.LoginUser;

@Controller
public class ImageController {

    @GetMapping({"","/","/image/feed"})
    public String feed(@AuthenticationPrincipal PrincipalDetails principal, HttpSession session) {
        System.out.println(principal.getUsername());
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        System.out.println(loginUser);
        return "image/feed";
    }
}
