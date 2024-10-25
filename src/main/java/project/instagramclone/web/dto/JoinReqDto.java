package project.instagramclone.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.instagramclone.domain.user.User;
import project.instagramclone.domain.user.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinReqDto {
    private String email;
    private String username;
    private String password;
    private String name;

    public User toEntity(){

        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .name(name)
                .role(UserRole.USER)
                .build();
    }

}
