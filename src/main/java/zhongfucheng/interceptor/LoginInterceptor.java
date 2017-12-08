package zhongfucheng.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zhongfucheng.entity.ActiveUser;
import zhongfucheng.entity.User;
import zhongfucheng.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * 登陆拦截器，用户在登陆时设置了“记住我”，那么下次用户访问时就会自动登陆
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {



        Subject currentUser = SecurityUtils.getSubject();

        //如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true 证明是没登陆直接通过记住我功能进来的
        if (!currentUser.isAuthenticated() && currentUser.isRemembered()) {

            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();


            //获取session看看是不是空的
            Session session = currentUser.getSession();
            if (session.getAttribute("currentUser") == null) {

                User user = userService.validateUserExist(activeUser.getUserEmail());

                UsernamePasswordToken token = new UsernamePasswordToken(user.getUserEmail(), activeUser.getPassword(), currentUser.isRemembered());

                //把当前用户放入session
                currentUser.login(token);

                session.setAttribute("currentUser",user);
                //设置会话的过期时间--ms,默认是30分钟，设置负数表示永不过期
                session.setTimeout(-1000l);

                //这是httpSession、用户页面获取数据的。
                httpServletRequest.getSession().setAttribute("activeUser", activeUser);

            }
        }

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
