package project.instagramclone.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.instagramclone.domain.comment.CommentRepository;
import project.instagramclone.domain.image.Image;
import project.instagramclone.domain.image.ImageRepository;
import project.instagramclone.domain.noti.NotiRepository;
import project.instagramclone.domain.noti.NotiType;
import project.instagramclone.handler.ex.MyImageIdNotFoundException;
import project.instagramclone.web.dto.CommentRespDto;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final NotiRepository notiRepository;

    @Transactional
    public void 댓글쓰기(CommentRespDto commentRespDto) {
        commentRepository.mSave(
                commentRespDto.getUserId(),
                commentRespDto.getImageId(),
                commentRespDto.getContent());
        Image imageEntity = imageRepository.findById(commentRespDto.getImageId()).orElseThrow(()->{
            return new MyImageIdNotFoundException();
        });
        notiRepository.mSave(commentRespDto.getUserId(), imageEntity.getUser().getId(), NotiType.COMMENT.name());

    }

    @Transactional
    public void 댓글삭제(int id){
        commentRepository.deleteById(id);
    }
}
