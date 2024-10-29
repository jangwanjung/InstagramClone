package project.instagramclone.domain.chatting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    private Long projectId;

    @Column(columnDefinition = "TEXT", nullable = false)
    //텍스트타입의 칼럼으로정의하고 null값을 허용하지않는다는 뜻이다
    private String sender;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @CreatedDate
    //@CreationTimestamp와 똑같다
    @Column(updatable = false)
    //한번저장된이후 변경이불가능하도록한다
    private LocalDateTime sendDateTime;
}
