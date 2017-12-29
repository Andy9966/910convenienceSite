package zhongfucheng.dao;

import zhongfucheng.entity.User;

import java.util.List;

/**
 * 用户Mapper
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public interface UserMapper extends BaseMapper<User> {




    /**
     * 验证邮箱是否存在，如果邮箱存在了那么不给予注册
     * @param userEmail
     * @return
     */
    User validateEmailExist(String userEmail);


    /**
     * 验证用户是否存在,被激活了的邮箱才算是真正的用户
     * @param userEmail
     * @return
     */
    User validateUserExist(String userEmail);


}