package project.instagramclone.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.instagramclone.domain.image.Image;
import project.instagramclone.domain.user.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRespDto {
    private boolean pageHost;  //페이지주인확인
    private User user;
    private List<UserProfileImageRespDto> images;
    private int followerCount;
    private int followingCount;
    private int imageCount;
    private boolean followState;
}
