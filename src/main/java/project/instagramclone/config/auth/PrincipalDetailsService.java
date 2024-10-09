package project.instagramclone.config.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.instagramclone.domain.user.User;
import project.instagramclone.domain.user.UserRepository;
import project.instagramclone.dto.LoginUser;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username).get();
        if(userEntity != null){
            System.out.println("유저있음");
            session.setAttribute("loginUser", new LoginUser(userEntity));
        }
        return new PrincipalDetails(userEntity);
    }

}