package project.instagramclone.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import project.instagramclone.util.Script;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/user/**","/follow/**","/image/**").authenticated() // 인증된 사용자만 접근 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ROLE_ADMIN 권한 필요
                        .anyRequest().permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/loginForm")
                        .loginProcessingUrl("/auth/login") // 로그인 처리 URL
                        .failureHandler(new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                                AuthenticationException exception) throws IOException, ServletException {
                                response.setContentType("text/html; charset=utf-8");
                                PrintWriter out = response.getWriter();
                                out.print(Script.back("유저네임 혹은 비밀번호를 찾을 수 없습니다."));
                                return;
                            }
                        })
                        .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 URL
                )
                /*.oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/loginForm")
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(principalOauth2UserService) // OAuth2 로그인 사용자 서비스
                        )
                        .failureUrl("/loginForm?error=true")
                        .defaultSuccessUrl("/")

                )*/
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 요청을 처리할 URL 설정
                        .logoutSuccessUrl("/auth/loginForm") // 로그아웃 성공 시 리다이렉트할 URL 설정
                        .addLogoutHandler((request, response, authentication) -> { // 로그아웃 핸들러 추가 (세션 무효화 처리)
                            HttpSession session = request.getSession();
                            session.invalidate();
                        })
                        .logoutSuccessHandler((request, response, authentication) -> // 로그아웃 성공 핸들러 추가 (리다이렉션 처리)
                                response.sendRedirect("/auth/loginForm"))
                        .deleteCookies("remember-me") // 로그아웃 시 쿠키 삭제 설정
                        .invalidateHttpSession(true) // HTTP session reset
                );


        return http.build();
    }
}
