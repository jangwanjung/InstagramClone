package project.instagramclone.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");  //역활의 선택지를 입력하자


    UserRole(String key) {   //UserRole을 사용하려면 key가 필요하다
        this.key = key;
    }
    
    private String key;
}
