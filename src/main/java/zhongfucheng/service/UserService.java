package zhongfucheng.service;

import zhongfucheng.entity.User;

import java.util.List;

/**
 * Created by ozc on 2017/10/25.
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
