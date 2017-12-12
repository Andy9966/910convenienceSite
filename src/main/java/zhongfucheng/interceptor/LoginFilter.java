package zhongfucheng.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import zhongfucheng.entity.ActiveUser;
import zhongfucheng.entity.User;
import zhongfucheng.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ozc on 2017/12/11.
 *
 * @author ozc
 * @version 1.0
 *
 * @deprecated  事实证明，在Shiro面前不能使用Filter来实现自动登陆。因此，具体的逻辑被我放到了表单验证器上了。
 */
public class LoginFilter implements Filter {


    @Autowired
    private UserService userService;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest httpRequest = (HttpServletRequest) req;
        Subject currentUser = SecurityUtils.getSubject();

        //如果 isAuthenticated 为 false 证明不是登录过的，同时 isRemember 为true 证明是没登陆直接通过记住我功能进来的
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
                httpRequest.getSession().setAttribute("activeUser", activeUser);

            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
