package zhongfucheng.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import zhongfucheng.entity.User;
import zhongfucheng.service.UserService;

import java.util.List;
import java.util.UUID;

/**
 * 用户业务类
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    /**
     * 判断邮件是否存在
     * @param userEmail
     * @return
     */
    public User validateEmailExist(String userEmail) {
        return userMapper.validateEmailExist(userEmail);
    }

    /**
     * 判断该用户是否被激活了
     * @param userEmail
     * @return
     */
    public User validateUserExist(String userEmail) {
        return userMapper.validateUserExist(userEmail);
    }

    /**
     * 使用HASH函数加密密码
     * @param user
     * @return
     */
    public User encryptedPassword(User user) {
        String salt = UUID.randomUUID().toString();
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(), salt, 2);
        user.setUserPassword(md5Hash.toString());
        user.setSalt(salt);
        return user;
    }

}
