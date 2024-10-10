package project.instagramclone.config;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import project.instagramclone.config.auth.LoginUserAnnotation;
import project.instagramclone.config.auth.dto.LoginUser;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer{

    private final HttpSession httpSession;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //HandlerMethodArgumentResolver는 컨트롤러에서 특정조건에맞는 파라미터가있을때 원하는값을 바인딩해주는 인터페이스이다
        resolvers.add(new HandlerMethodArgumentResolver() {
            // 1. supportsParameter() 에서 true가 리턴되면!!
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUserAnnotation.class) != null;
                //파라미터에 LoginUserAnnotation이 붙어있는지확인한다
                boolean isUserClass = LoginUser.class.equals(parameter.getParameterType());
                //파라미터에 타입이 LoginUser클래스인지 확인한다
                return isLoginUserAnnotation && isUserClass;
            }

            // 2. 세션을 만들어서 @LoginUserAnnotation 에 주입해준다.
            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                          NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                return httpSession.getAttribute("loginUser");
            }
        });
    }

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/resources/upload/**").addResourceLocations(uploadFolder)
                .setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
        //첫번째는 upload의 주소 //두번째는 upload폴더의 절대경로 //세번째는 캐시기간설정

    }
}