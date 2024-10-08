package project.instagramclone.domain.image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import project.instagramclone.domain.tag.Tag;
import project.instagramclone.domain.user.User;

import java.sql.Timestamp;
import java.util.List;

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

    @CreationTimestamp
    private Timestamp createDate;
}
