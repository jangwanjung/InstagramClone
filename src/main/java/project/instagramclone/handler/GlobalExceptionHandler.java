package project.instagramclone.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import project.instagramclone.handler.ex.MyImageIdNotFoundException;
import project.instagramclone.handler.ex.MyUserIdNotFoundException;
import project.instagramclone.handler.ex.MyUsernameNotFoundException;
import project.instagramclone.util.Script;

@RestController
@ControllerAdvice //전역적으로 예외를 처리할수있도록 핸들링을 할수있게만들어주는 어노테이션이다
public class GlobalExceptionHandler { //이것을 사용하면 모든 컨트롤러에서 발상하는 예외를 이클래스에서 처리할수있다

    @ExceptionHandler(value = MyUserIdNotFoundException.class)  //예외가발생했을때 이 메서드가 호출도되도록 지정한다
    public String myUserIdNotFoundException(Exception e){
        //Exception e는 예외객체를 e로 받는다는뜻이다
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(value = MyUsernameNotFoundException.class)
    public String myUsernameNotFoundException(Exception e){
        return Script.alert(e.getMessage());
    }

    @ExceptionHandler(value =IllegalArgumentException.class)
    public String myIllegalArgumentException(Exception e){
        return Script.alert(e.getMessage());
    }

    @ExceptionHandler(value = MyImageIdNotFoundException.class)
    public String myImageIdNotFoundException(Exception e){
        return Script.alert(e.getMessage());
    }
}
