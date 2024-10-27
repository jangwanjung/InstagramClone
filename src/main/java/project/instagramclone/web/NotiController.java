package project.instagramclone.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.instagramclone.service.NotiService;

@RequiredArgsConstructor
@Controller
public class NotiController {
    private final NotiService notiService;

    @GetMapping("/noti/{loginUserId}")
    public String noti(@PathVariable int loginUserId, Model model) {
        model.addAttribute("notis", notiService.알림리스트(loginUserId));
        return "noti/noti";
    }
}
