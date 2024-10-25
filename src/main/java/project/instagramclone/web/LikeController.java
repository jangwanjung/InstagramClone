package project.instagramclone.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.instagramclone.config.auth.LoginUserAnnotation;
import project.instagramclone.config.auth.dto.LoginUser;
import project.instagramclone.domain.like.LikesRepository;
import project.instagramclone.service.LikesService;

@RequiredArgsConstructor
@Controller
public class LikeController {

    private final LikesRepository likesRepository;
    private final LikesService likesService;

    @PostMapping("/likes/{imageId}")
    public ResponseEntity<?> like(@PathVariable int imageId, @LoginUserAnnotation LoginUser loginUser){
        likesService.좋아요(imageId, loginUser.getId());
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

    @DeleteMapping("/likes/{imageId}")
    public ResponseEntity<?> delete(@PathVariable int imageId, @LoginUserAnnotation LoginUser loginUser){
        likesService.싫어요(imageId, loginUser.getId());
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
}
