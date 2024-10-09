package project.instagramclone.util;

public class Script {
    public static String alert(String msg){
        StringBuilder sb= new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("</script>");
        //<script>alert('msg')</script>
        return sb.toString();
    }

    public static String back(String msg){
        StringBuilder sb= new StringBuilder();
        sb.append("<script>");
        sb.append("alert('"+msg+"');");
        sb.append("history.back();");
        sb.append("</script>");
        //<sciprt>alert('mas');history.back();</script>
        return sb.toString();
    }
}
