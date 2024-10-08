package project.instagramclone.dto;

import lombok.Data;
import project.instagramclone.domain.user.User;
import project.instagramclone.domain.user.UserRole;

@Data
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
