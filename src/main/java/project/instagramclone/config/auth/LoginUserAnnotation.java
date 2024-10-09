package project.instagramclone.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어노테이션을 만드는 코드이다
@Target(ElementType.PARAMETER) //타입이 파라미터일때만 어노테이션을적용한다
@Retention(RetentionPolicy.RUNTIME)  //런타임일때만 어노테이션을 적용한다
public @interface LoginUserAnnotation {
}
