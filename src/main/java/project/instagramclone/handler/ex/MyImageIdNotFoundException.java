package project.instagramclone.handler.ex;

public class MyImageIdNotFoundException extends RuntimeException{

    private String massege;

    public MyImageIdNotFoundException(){
        this.massege = "해당 이미지의 id를 찾을 수 없습니다.";
    }

    public MyImageIdNotFoundException(String massege){
        this.massege = massege;
    }

    public String getMassege() {
        return massege;
    }
}
