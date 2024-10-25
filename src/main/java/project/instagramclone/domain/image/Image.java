package project.instagramclone.domain.image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import project.instagramclone.domain.comment.Comment;
import project.instagramclone.domain.like.Likes;
import project.instagramclone.domain.tag.Tag;
import project.instagramclone.domain.user.User;
import project.instagramclone.web.dto.UserProfileImageRespDto;

import java.sql.Timestamp;
import java.util.List;

@SqlResultSetMapping(
        name = "UserProfileImageRespDtoMapping",
        classes = @ConstructorResult(
                targetClass = UserProfileImageRespDto.class,
                columns = {
                        @ColumnResult(name="id", type = Integer.class),
                        @ColumnResult(name="imageUrl", type = String.class),
                        @ColumnResult(name="likeCount", type = Integer.class),
                        @ColumnResult(name="commentCount", type = Integer.class),
                }
        )
)

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;

    private String caption;

    private String imageUrl;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)  //image를 select하면 한명의 User가 딸려옴
    private User user;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) //mappedBy="Image"뜻 tag객체가 주인이된다
    //즉 tag객체는 image테이블의 데이터를 수정할수있지만 image객체는 tage객체의 데이터를 수정할수없다
    //되도록 외래키가있는곳에 mappedBy="Image"를 넣자
    @JsonIgnoreProperties({"image"})
    private List<Tag> tags;

    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Comment> comments;

    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Likes> likes;

    @CreationTimestamp
    private Timestamp createDate;

    @Transient //이 어노테이션을 사용하면 테이블에 칼럼이안만들어진다
    private int likeCount;

    @Transient
    private boolean likeState;
}
