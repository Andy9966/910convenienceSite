package zhongfucheng.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import zhongfucheng.entity.User;
import zhongfucheng.service.UserService;

import java.util.List;
import java.util.UUID;

/**
 * Created by ozc on 2017/10/25.
 */

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    public User validateEmailExist(String userEmail) {
        return userMapper.validateEmailExist(userEmail);
    }

    public User validateUserExist(String userEmail) {
        return userMapper.validateUserExist(userEmail);
    }

    public User encryptedPassword(User user) {
        String salt = UUID.randomUUID().toString();
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(), salt, 2);
        user.setUserPassword(md5Hash.toString());
        user.setSalt(salt);
        return user;
    }

}
