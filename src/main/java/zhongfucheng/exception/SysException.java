package zhongfucheng.exception;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import zhongfucheng.utils.FreeMarkerUtils;
import zhongfucheng.utils.ReadPropertiesUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ozc on 2017/10/30.
 */

/**
 * 统一异常处理类
 */
public class SysException  implements HandlerExceptionResolver {


    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {

        //输出异常
        ex.printStackTrace();

        String message = null;
        UserException userException = null;

        //如果ex是系统 自定义的异常，直接取出异常信息
        if (ex instanceof UserException) {
            userException = (UserException) ex;
        } else {
            //针对非UserException异常，对这类重新构造成一个UserException，异常信息为“未知错误”
            userException = new UserException("未知错误");
        }

        message = userException.getMessage();
        request.setAttribute("message", message);


        try {
            //根据模版生成页面，重定向到页面中
            Map<String, Object> map = new HashedMap();
            map.put("title", "错误页面");
            map.put("content", message);
            map.put("subject", "出错啦");
            map.put("path", ReadPropertiesUtil.readProp("projectPath"));
            FreeMarkerUtils markerUtils = new FreeMarkerUtils();
            markerUtils.ouputFile("promptPages.ftl", "promptPages.html", map);

            request.getRequestDispatcher("/promptPages.html").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ModelAndView();
    }


}
