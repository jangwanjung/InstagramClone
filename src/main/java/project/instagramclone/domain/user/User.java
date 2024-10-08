package project.instagramclone.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.management.relation.Role;
import java.sql.Timestamp;

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

    private String provider;

    private String providerId;

    @CreationTimestamp
    private Timestamp createDate;

}
