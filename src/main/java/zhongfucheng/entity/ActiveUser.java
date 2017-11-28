package zhongfucheng.entity;

import java.io.Serializable;

/**
 * Created by ozc on 2017/10/30.
 */

/**
 * 存储用户的身份信息
 */
public class ActiveUser implements Serializable {
    private String userId;
    private String usercEmail;
    private String userNickname;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsercEmail() {
        return usercEmail;
    }

    public void setUsercEmail(String usercEmail) {
        this.usercEmail = usercEmail;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
