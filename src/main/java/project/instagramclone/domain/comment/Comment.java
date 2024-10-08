package project.instagramclone.domain.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import project.instagramclone.domain.image.Image;
import project.instagramclone.domain.user.User;

import java.sql.Timestamp;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne  //여러개의 comment를 하나의 user가 씀
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "iamgeId")
    private Image image;

    @CreationTimestamp
    private Timestamp createDate;





}
