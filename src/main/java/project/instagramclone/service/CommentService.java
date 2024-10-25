package project.instagramclone.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.instagramclone.domain.comment.CommentRepository;
import project.instagramclone.web.dto.CommentRespDto;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void 댓글쓰기(CommentRespDto commentRespDto) {
        commentRepository.mSave(
                commentRespDto.getUserId(),
                commentRespDto.getImageId(),
                commentRespDto.getContent());
    }
}
