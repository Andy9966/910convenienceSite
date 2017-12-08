package zhongfucheng.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import zhongfucheng.entity.ActiveUser;
import zhongfucheng.utils.ReadPropertiesUtil;
import zhongfucheng.utils.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 自定义一个表单过滤器的目的就是认证流程由自己控制
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public class UserFormAuthenticationFilter extends FormAuthenticationFilter {


    /**
     * 只要请求地址不是post请求和不是user/login（处理登陆的url），那么就返回登陆页面上
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //判断是否是登陆页面地址请求地址、如果不是那么重定向到controller的方法中
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {

                //在提交给realm查询前，先判断验证码
                if (WebUtils.validateCaptcha(httpRequest)) {
                    return executeLogin(request, response);

                } else {
                    if (isAjax(httpRequest)) {

                        //这里要使用标准的JSON格式
                        WebUtils.printCNJSON("{\"message\":\"验证码错误\"}", httpServletResponse);

                        return false;

                    } else {
                        // 放行 allow them to see the login page ;)
                        httpRequest.setAttribute("shiroLoginFailure", "captchaCodeError");
                        return true;
                    }
                }

            } else {
                // 放行 allow them to see the login page ;)
                return true;
            }
        } else {

            // TODO AJAX请求用户扩展。以后再补
            if (isAjax(httpRequest)) {
                //httpServletResponse.sendError(ShiroFilterUtils.HTTP_STATUS_SESSION_EXPIRE);
                return false;
            } else {

                //返回配置的user/login.do，该方法会重定向到登陆页面地址，再次发送请求给本方法
                saveRequestAndRedirectToLogin(request, response);
            }

            return false;
        }
    }

    /**
     * 认证成功，把用户认证信息保存在session中，判断是否为ajax请求
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        //在跳转前将数据保存到session中
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();

        subject.getSession().setAttribute("activeUser", activeUser);


        HttpSession session = httpRequest.getSession();
        session.setAttribute("activeUser", activeUser);


        //如果是ajax请求，那么我们手动跳转
        //如果不是ajax请求，那么由Shiro帮我们跳转
        if (isAjax(httpRequest)) {
            WebUtils.printCNJSON("{\"message\":\"登陆成功\"}", httpServletResponse);
        } else {
            //设置它跳转到首页路径，如果不设置它还会停留在登陆页面。
            String indexPath = ReadPropertiesUtil.readProp("projectPath") + "/index.html";
            org.apache.shiro.web.util.WebUtils.redirectToSavedRequest(request, response, indexPath);
        }
        return false;

    }

    /**
     * 认证失败、如果ajax请求则返回json数据
     * 如果非ajax请求、则默认返回给login.do处理异常
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {


        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 不是ajax请求，就按照源码的方式去干(返回异常给controller，controller处理异常)
        if (!isAjax(httpServletRequest)) {
            setFailureAttribute(request, e);
            return true;
        }

        //是ajax请求，我们返回json给浏览器

        String message = e.getClass().getSimpleName();

        if ("IncorrectCredentialsException".equals(message)) {
            WebUtils.printCNJSON("{\"message\":\"密码错误\"}", httpServletResponse);
        } else if ("UnknownAccountException".equals(message)) {
            WebUtils.printCNJSON("{\"message\":\"账号不存在\"}", httpServletResponse);
        } else if ("captchaCodeError".equals(message)) {
            WebUtils.printCNJSON("{\"message\":\"验证码错误\"}", httpServletResponse);
        } else {
            WebUtils.printCNJSON("{\"message\":\"未知错误\"}", httpServletResponse);
        }
        return false;
    }

    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
