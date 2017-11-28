package zhongfucheng.exception;

/**
 * Created by ozc on 2017/10/27.
 */

/**
 * 用户模块的异常
 */
public class UserException extends Exception {

    //异常信息
    private String message;

    public UserException(String message){
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
