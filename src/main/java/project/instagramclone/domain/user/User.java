package project.instagramclone.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import project.instagramclone.web.dto.FollowRespDto;

import javax.management.relation.Role;
import java.sql.Timestamp;

@SqlResultSetMapping(
        name = "FollowRespDtoMapping",
        classes = @ConstructorResult(
                targetClass = FollowRespDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "profileImage", type = String.class),
                        @ColumnResult(name = "followState", type = Boolean.class),
                        @ColumnResult(name = "equalUserState", type = Boolean.class),

                }
        )
)

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private String website;

    private String bio;

    private String phone;

    private String gender;

    private String profileImage;

    @Enumerated(EnumType.STRING)  //열거형으로타입을 데이터베이스에 String값으로 저장하겠다는 뜻
    private UserRole role;

    private String email;

    private String provider;

    private String providerId;

    @CreationTimestamp
    private Timestamp createDate;

}
