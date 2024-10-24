package project.instagramclone.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.instagramclone.config.auth.LoginUserAnnotation;
import project.instagramclone.web.dto.ImageReqDto;
import project.instagramclone.config.auth.dto.LoginUser;
import project.instagramclone.service.ImageService;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"","/","/image/feed"})
    public String feed(@LoginUserAnnotation LoginUser loginUser) {
        return "image/feed";
    }

    @GetMapping("/image/uploadForm")
    public String imageUploadForm() {
        return "image/image-upload";

    }

    @PostMapping("/image")
            public String imageUpload(@LoginUserAnnotation LoginUser loginUser, ImageReqDto imageReqDto){
        imageService.사진업로드(imageReqDto, loginUser.getId());
        return "redirect:/";
    }

    @GetMapping("/image/explore")
    public String imageExplore(@LoginUserAnnotation LoginUser loginUser, Model model){
        model.addAttribute("images", imageService.인기사진(loginUser.getId()));
        return "image/explore";
    }
}
