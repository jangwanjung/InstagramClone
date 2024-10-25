package project.instagramclone.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByUserId(int id);

    @Query(value = "select * from image where userId in (select id from user where id=?1 and id not in " +
            "(select toUserId from follow where fromUserId = ?1)) limit 20", nativeQuery = true)
    List<Image> mNonFollowImage(int loginUserId);
    //내가 팔로우하지않은사람의 image는 최대20개만 반환


    @Query(value = "select * from image where userId in (select toUserId from follow where fromUserId =?1)", nativeQuery = true)
    List<Image> mFeed(int loginUserId);
    //내가 팔로우하고있는사람들의 image를 모두반환


}
