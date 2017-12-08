package zhongfucheng.exception;

/**
 * 收藏模块异常信息
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
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
