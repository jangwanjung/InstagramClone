package project.instagramclone.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileImageRespDto {
    private int id;
    private String imageUrl;
    private int likeCount;
    private int commentCount;
}
