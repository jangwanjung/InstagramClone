package project.instagramclone.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query(value = "select u.*, (select true from follow where fromUserId = ?2 and toUserId = u.id)" +
            " as matpal from follow f inner join user u on f.toUserId = u.id and f.fromUserId = ?1", nativeQuery = true)
    List<User> mFollowingUser(int pageUserId, int loginUserId);
    //내가 팔로우하는사람 목록조회하고 내가 팔로우하고있는지 조회

    @Query(value = "select u.*,(select true from follow where fromUserId = ?2 and toUserId = u.id)" +
            " as matpal from follow f inner join user u on f.fromUserId = u.id and f.toUserId = ?1", nativeQuery = true)
    List<User> mFollowersUser(int pageUserId, int loginUserId);
    //나를 팔로우하는사람 목록조회하고 나를 팔로우하고있는지 조회
}
