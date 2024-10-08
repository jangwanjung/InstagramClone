package project.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.instagramclone.domain.user.UserRepository;
import project.instagramclone.dto.JoinReqDto;

@RequiredArgsConstructor //생성자 주입을 임의의 코드없이 자동으로 설정해주는어노테이션이다
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void 회원가입(JoinReqDto joinReqDto) {
        String encPassword = bCryptPasswordEncoder.encode(joinReqDto.getPassword());
        joinReqDto.setPassword(encPassword);
        userRepository.save(joinReqDto.toEntity());
    }
}
