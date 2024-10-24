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
    //image의 모든것을찾자(userid에서

    @Query(value = "select * from image where userId in (select toUserId from follow where fromUserId =?1)", nativeQuery = true)
    List<Image> mFeed(int loginUserId);


}
