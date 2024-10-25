package project.instagramclone.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    @Query(value = "SELECT count(*) FROM follow WHERE toUserId = ?1", nativeQuery = true)
    int mCountByFollower(int toUserId);
    //follow 테이블에서 내가 팔로우하는 사람의 수를 반환해준다

    @Query(value = "SELECT count(*) FROM follow WHERE fromUserId = ?1", nativeQuery = true)
    int mCountByFollowing(int fromUserId);
    //follow 테이블에서 나를 팔로우하는 사람의 수를 반환해준다

    @Query(value = "SELECT count(*) FROM follow WHERE fromUserId = ?1 AND toUserId = ?2", nativeQuery = true)
    int mFollowState(int loginUserId, int pageUserId);
    //follow 테이블에서 내가팔로우하면서 상대방이 나를 팔로우할때 1을 반환하고 아닐경우 0을 반환한다

    @Modifying //수정이나 삭제시 필요한 어노테이션이다
    @Query(value = "INSERT INTO follow(fromUserId, toUserId) VALUES(?1, ?2)", nativeQuery = true)
    int mFollow(int loginUserId, int pageUserId);
    //follow 테이블에서 누가 누구를 팔로우했는지 추가해준다

    @Modifying //수정이나 삭제시 필요한 어노테이션이다
    @Query(value = "DELETE FROM follow WHERE fromUserId = ?1 AND toUserId = ?2", nativeQuery = true)
    int mUnFollow(int loginUserId, int pageUserId);
    //follow 테이블에서 누가 누구를 언팔로우했는지 삭제해준다

}
