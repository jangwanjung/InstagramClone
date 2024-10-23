package project.instagramclone.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.instagramclone.config.auth.LoginUserAnnotation;
import project.instagramclone.config.auth.dto.LoginUser;
import project.instagramclone.domain.follow.FollowRepository;
import project.instagramclone.service.FollowService;

@RequiredArgsConstructor
@Controller
public class FollowController {

    private final FollowService followService;

    @GetMapping("/follow/followingList/{pageUserId}")
    public String followingList(@PathVariable int pageUserId, @LoginUserAnnotation LoginUser loginUser, Model model) {
        model.addAttribute("followingList", followService.팔로잉리스트(loginUser.getId(), pageUserId));
        return "follow/following-List";
    }
    @GetMapping("/follow/followerList/{pageUserId}")
    public String followerList(@PathVariable int pageUserId, @LoginUserAnnotation LoginUser loginUser, Model model) {
        model.addAttribute("followerList", followService.팔로워리스트(loginUser.getId(), pageUserId));
        return "follow/follower-List";
    }

    @PostMapping("/follow/{id}")
    public ResponseEntity<?> follow(@PathVariable int id, @LoginUserAnnotation LoginUser loginUser) {
        followService.팔로우(loginUser.getId(), id);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

    @DeleteMapping("/follow/{id}")
    public ResponseEntity<?> unfollow(@PathVariable int id, @LoginUserAnnotation LoginUser loginUser) {
        followService.팔로우취소(loginUser.getId(), id);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
}
