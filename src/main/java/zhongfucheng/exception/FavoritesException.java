package zhongfucheng.exception;

/**
 * Created by ozc on 2017/11/6.
 */

/**
 * 收藏模块异常信息
 */
public class FavoritesException extends Exception   {

    //异常信息
    private String message;

    public FavoritesException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
