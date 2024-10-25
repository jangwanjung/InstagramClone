package project.instagramclone.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

    @Modifying
    @Query(value = "insert into likes(imageId, userId) valuse(?1, ?2)" ,nativeQuery = true)
    int mSave(int imageId, int loginUserId);
    //좋아요를 누르면 likes테이블에 삽입

    @Modifying
    @Query(value = "delete from likes where imageId = ?1 and userId = ?2", nativeQuery = true)
    int mDelete(int imageId, int loginUserId);
    //좋아요취소를 누르면 likes테이블에서 삭제



}
