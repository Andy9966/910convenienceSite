package zhongfucheng.exception;

/**
 * 用户模块的异常
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public class InitException extends Exception {

    //异常信息
    private String message;

    public InitException(String message){
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
