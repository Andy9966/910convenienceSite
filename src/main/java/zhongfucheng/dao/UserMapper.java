package zhongfucheng.dao;

import zhongfucheng.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


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


}