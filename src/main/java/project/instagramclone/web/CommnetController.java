package project.instagramclone.web;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.instagramclone.service.CommentService;
import project.instagramclone.web.dto.CommentRespDto;

@RequiredArgsConstructor
@Controller
public class CommnetController {


    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> comment(CommentRespDto commentRespDto){
        commentService.댓글쓰기(commentRespDto);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> commentDelete(@PathVariable int id){
        commentService.댓글삭제(id);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }


}
