package zhongfucheng.exception;

/**
 * 用户模块异常信息
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
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
