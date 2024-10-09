package project.instagramclone.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project.instagramclone.config.auth.PrincipalDetails;

@Controller
public class ImageController {

    @GetMapping({"","/","/image/feed"})
    public String feed(@AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println(principal.getUsername());
        a
        return "image/feed";
    }
}
