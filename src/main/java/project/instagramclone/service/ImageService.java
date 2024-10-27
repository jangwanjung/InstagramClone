package project.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.instagramclone.domain.comment.Comment;
import project.instagramclone.domain.image.Image;
import project.instagramclone.domain.image.ImageRepository;
import project.instagramclone.domain.like.Likes;
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

    @Transactional(readOnly = true)
    public List<Image> 인기사진(int loginUserId){
        return imageRepository.mNonFollowImage(loginUserId);
    }

    @Transactional
    public List<Image> 피드사진(int loginUserId, String tag){
        List<Image> images = null;
        if(tag == null || tag.equals("")){
            images = imageRepository.mFeed(loginUserId);
        }
        else{
            images = imageRepository.mFeed(tag);
        }
        for (Image image : images){
            image.setLikeCount(image.getLikes().size());
            //좋아요 상태 여부 등록
            for(Likes like : image.getLikes()){
                if(like.getUser().getId() == loginUserId){
                    image.setLikeState(true);
                }
            }
            //댓글 주인 여부 등록
            for(Comment comment : image.getComments()){
                if(comment.getUser().getId() == loginUserId){
                    comment.setCommentHost(true);
                }
            }
        }
        return images;
    }

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
