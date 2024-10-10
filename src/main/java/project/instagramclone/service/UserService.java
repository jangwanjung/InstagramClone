package project.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.instagramclone.config.auth.dto.LoginUser;
import project.instagramclone.domain.image.Image;
import project.instagramclone.domain.image.ImageRepository;
import project.instagramclone.domain.user.User;
import project.instagramclone.domain.user.UserRepository;
import project.instagramclone.handler.ex.MyUserIdNotFoundException;
import project.instagramclone.web.dto.JoinReqDto;
import project.instagramclone.web.dto.UserProfileRespDto;

import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor //생성자 주입을 임의의 코드없이 자동으로 설정해주는어노테이션이다
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImageRepository imageRepository;

    @Transactional
    public void 회원가입(JoinReqDto joinReqDto) {
        String encPassword = bCryptPasswordEncoder.encode(joinReqDto.getPassword());
        joinReqDto.setPassword(encPassword);
        userRepository.save(joinReqDto.toEntity());
    }

    @Transactional(readOnly = true)
    public UserProfileRespDto 회원프로필(int id, LoginUser loginUser) {

        int imageCount;
        int followerCount;
        int followingCount;

        User userEntity = userRepository.findById(id)
                .orElseThrow(new Supplier<MyUserIdNotFoundException>(){
                    //Supplier은 매개변수를 받지않고 단순히 반환한다
                    @Override
                    public MyUserIdNotFoundException get() {
                        return new MyUserIdNotFoundException();
                    }
                });
        // 1.이미지들과 전체 이미지 카운트
        List<Image> imagesEntity = imageRepository.findByUserId(id);
        imageCount = imagesEntity.size();
        // 2.팔로우 수 조회(수정해야함)
        followerCount = 50;
        followingCount = 100;
        // 3.최종마무리
        UserProfileRespDto userProfileRespDto =
                UserProfileRespDto.builder()
                        .pageHost(id== loginUser.getId()) //조회한사람이 본인일때 true반환
                        .followerCount(followerCount)
                        .followingCount(followingCount)
                        .imageCount(imageCount)
                        .user(userEntity)
                        .images(imagesEntity)
                        .build();

        return userProfileRespDto;
    }
}
