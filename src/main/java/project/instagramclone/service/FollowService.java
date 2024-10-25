package project.instagramclone.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.instagramclone.domain.follow.FollowRepository;
import project.instagramclone.util.Script;
import project.instagramclone.web.dto.FollowRespDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    @PersistenceContext
    private EntityManager em;
    private final FollowRepository followRepository;

    @Transactional
    public void 팔로우(int loginUserId, int pageUserId){
        followRepository.mFollow(loginUserId, pageUserId);
    }

    @Transactional
    public void 팔로우취소(int loginUserId, int pageUserId){
        followRepository.mUnFollow(loginUserId, pageUserId);
    }

    public List<FollowRespDto> 팔로잉리스트(int loginUserId, int pageUserId){
        StringBuilder sb = new StringBuilder();
        sb.append("select u.id,u.username,u.name,u.profileImage, ");
        sb.append("if(u.id = ?, true, false) equalUserState,");
        sb.append("if((select true from follow where fromUserId = ? and toUserId = u.id), true, false) as followState ");
        sb.append("from follow f inner join user u on f.toUserId = u.id ");
        sb.append("and f.fromUserId = ?");
        String q = sb.toString();

        Query query = em.createNativeQuery(q, "FollowRespDtoMapping")
                .setParameter(1, loginUserId)
                .setParameter(2, loginUserId)
                .setParameter(3, pageUserId);
        List<FollowRespDto> followListEntity = query.getResultList();
        return followListEntity;
    }

    public List<FollowRespDto> 팔로워리스트(int loginUserId, int pageUserId){
        StringBuilder sb = new StringBuilder();
        sb.append("select u.id,u.username,u.name,u.profileImage, ");
        sb.append("if(u.id = ?, true, false) equalUserState,");
        sb.append("if((select true from follow where fromUserId = ? and toUserId = u.id), true, false) as followState ");
        sb.append("from follow f inner join user u on f.fromUserId = u.id ");
        sb.append("and f.toUserId = ?");
        String q = sb.toString();

        Query query = em.createNativeQuery(q, "FollowRespDtoMapping")
                .setParameter(1, loginUserId)
                .setParameter(2, loginUserId)
                .setParameter(3, pageUserId);
        List<FollowRespDto> followerListEntity = query.getResultList();
        return followerListEntity;
    }
}
