package project.instagramclone.domain.chatting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChattingRepository extends JpaRepository<Chatting, Integer> {

    @Modifying
    @Query(value = "Insert into chatting(fromUserId, toUserId, message, createDate) values(?1,?2,?3,now())", nativeQuery = true)
    int mChatSave(int fromUserId, int toUserId, String message);
    //chatting테이블에다가 삽입

    List<Chatting> findByFromUserIdAndToUserId(int fromUserId, int toUserId);
    //fromUserId와 toUserId가 알맞는 것을 Chatting테이블에서 가져와 리스트화

    @Query("select m from Chatting as m where (m.fromUser.id = :fromUserId and m.toUser.id = :toUserId) or " +
            "m.toUser.id = :fromUserId and m.fromUser.id = :toUserId")
    List<Chatting> mChatList(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);
    //fromUserId가 메시지를 보내고 toUserId가 메시지를 받는경우의 모든 값을 리스트화해서 가져온다
    //toUserId가 메시지를 보내고 fromUserId가 메시지를 받는경의 모든값을 리스트화해서 가져온다
}
