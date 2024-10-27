package project.instagramclone.domain.noti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotiRepository extends JpaRepository<Noti, Integer> {

    @Modifying
    @Query(value = "INSERT INTO noti(fromUserId, toUserId, notiType, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(int fromUserId, int toUserId, String notiType);

    List<Noti> findByToUserId(int loginUserId);
}
