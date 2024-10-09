package project.instagramclone.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import project.instagramclone.domain.image.Image;
import project.instagramclone.domain.user.User;

@Data
public class ImageReqDto {
    private MultipartFile file;
    private String caption;
    private String location;
    private String tags;

    public Image toEntity(String imageUrl, User userEntity){
        return Image.builder()
                .location(location)
                .caption(caption)
                .imageUrl(imageUrl)
                .user(userEntity)
                .build();
    }
}
