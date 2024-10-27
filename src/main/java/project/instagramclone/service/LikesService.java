package project.instagramclone.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.instagramclone.domain.image.Image;
import project.instagramclone.domain.image.ImageRepository;
import project.instagramclone.domain.like.LikesRepository;
import project.instagramclone.domain.noti.Noti;
import project.instagramclone.domain.noti.NotiRepository;
import project.instagramclone.domain.noti.NotiType;
import project.instagramclone.handler.ex.MyImageIdNotFoundException;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private final ImageRepository imageRepository;
    private final NotiRepository notiRepository;

    @Transactional
    public void 좋아요(int imageId, int loginUserId){
        likesRepository.mSave(imageId, loginUserId);
        Image imageEntity = imageRepository.findById(imageId).orElseThrow(()->{
            return new MyImageIdNotFoundException();
        });
        notiRepository.mSave(loginUserId, imageEntity.getUser().getId(), NotiType.LIKE.name());
    }

    @Transactional
    public void 싫어요(int imageId, int loginUserId){
        likesRepository.mDelete(imageId, loginUserId);
    }
}
