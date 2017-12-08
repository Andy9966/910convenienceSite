package zhongfucheng.service;

import zhongfucheng.entity.User;

import java.util.List;

/**
 * 用户业务接口
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public interface UserService extends BaseService<User> {

    /**
     * 验证邮箱是否存在
     * @param userEmail
     * @return
     */
    User validateEmailExist(String userEmail);


    /**
     * 验证用户是否存在,被激活了的邮箱才算是存在的。
     * @param userEmail
     * @return
     */
    User validateUserExist(String userEmail);


    /**
     * 对用户的密码进行加密
     * @param user
     * @return
     */
    User encryptedPassword(User user);
}
