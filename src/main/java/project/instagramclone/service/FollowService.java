package project.instagramclone.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.instagramclone.domain.follow.FollowRepository;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;

    @Transactional
    public void 팔로우(int loginUserId, int pageUserId){
        followRepository.mFollow(loginUserId, pageUserId);
    }

    @Transactional
    public void 팔로우취소(int loginUserId, int pageUserId){
        followRepository.mUnFollow(loginUserId, pageUserId);
    }
}
