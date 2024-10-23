package project.instagramclone.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    @Query(value = "SELECT count(*) FROM follow WHERE toUserId = ?1", nativeQuery = true)
    int mCountByFollower(int toUserId);

    @Query(value = "SELECT count(*) FROM follow WHERE fromUserId = ?1", nativeQuery = true)
    int mCountByFollowing(int fromUserId);

    @Query(value = "SELECT count(*) FROM follow WHERE fromUserId = ?1 AND toUserId = ?2", nativeQuery = true)
    int mFollowState(int loginUserId, int pageUserId);

    @Modifying //수정이나 삭제시 필요한 어노테이션이다
    @Query(value = "INSERT INTO follow(fromUserId, toUserId) VALUES(?1, ?2)", nativeQuery = true)
    int mFollow(int loginUserId, int pageUserId);

    @Modifying //수정이나 삭제시 필요한 어노테이션이다
    @Query(value = "DELETE FROM follow WHERE fromUserId = ?1 AND toUserId = ?2", nativeQuery = true)
    int mUnFollow(int loginUserId, int pageUserId);

}
