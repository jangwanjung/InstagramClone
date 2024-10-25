package project.instagramclone.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.instagramclone.config.auth.LoginUserAnnotation;
import project.instagramclone.config.auth.dto.LoginUser;
import project.instagramclone.domain.user.User;
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

    @GetMapping("/user/profileEdit")
    public String profileEdit(@LoginUserAnnotation LoginUser loginUser, Model model) {
        User userEntity = userService.회원정보(loginUser);
        model.addAttribute("user", userEntity);
        return "user/profile-edit";
    }

    @PostMapping("/user/profileUpload")
    public String userProfileUpload(@RequestParam("profileImage") MultipartFile file, int userId,
                                    @LoginUserAnnotation LoginUser loginUser, Model model) {
        if(userId == loginUser.getId()){
            userService.프로필사진업로드(loginUser, file);
        }
        return "redirect:/user/"+userId;
    }

    @PutMapping("/user")
    public ResponseEntity<?> userUpdate(User user){
        userService.회원수정(user);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }


}
