package project.instagramclone.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.instagramclone.domain.like.LikesRepository;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;

    @Transactional
    public void 좋아요(int imageId, int loginUserId){
        likesRepository.mSave(imageId, loginUserId);
    }

    @Transactional
    public void 싫어요(int imageId, int loginUserId){
        likesRepository.mDelete(imageId, loginUserId);
    }
}
