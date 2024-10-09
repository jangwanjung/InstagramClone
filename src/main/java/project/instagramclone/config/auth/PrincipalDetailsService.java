package project.instagramclone.config.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.instagramclone.domain.user.User;
import project.instagramclone.domain.user.UserRepository;
import project.instagramclone.web.dto.LoginUser;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username).
                map(new Function<User, User>() {
                    @Override
                    public User apply(User t) {
                        session.setAttribute("loginUser", new LoginUser(t));
                        return t;
                    }
                })
                .orElse(null);

        return new PrincipalDetails(userEntity);
    }

}