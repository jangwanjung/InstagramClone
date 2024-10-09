package project.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.instagramclone.domain.image.Image;
import project.instagramclone.domain.image.ImageRepository;
import project.instagramclone.domain.tag.Tag;
import project.instagramclone.domain.tag.TagRepository;
import project.instagramclone.domain.user.User;
import project.instagramclone.domain.user.UserRepository;
import project.instagramclone.web.dto.ImageReqDto;
import project.instagramclone.util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageReqDto imageReqDto, int userId) {
        User userEntity = userRepository.findById(userId).orElseThrow(null);
        UUID uuid = UUID.randomUUID();
        String imageFilename = uuid+"_"+imageReqDto.getFile().getOriginalFilename();
        Path imageFilepath = Paths.get(uploadFolder+imageFilename);
        try {
            Files.write(imageFilepath, imageReqDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //1. image저장
        Image image = imageReqDto.toEntity(imageFilename, userEntity);
        Image imageEntity = imageRepository.save(image);

        //2. tag저장
        List<String> tagNames = Utils.tagParse(imageReqDto.getTags());
        for (String name : tagNames){
            Tag tag = Tag.builder()
                    .image(imageEntity)
                    .name(name)
                    .build();
            tagRepository.save(tag);
        }

    }
}
