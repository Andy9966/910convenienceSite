package zhongfucheng.listener;

import org.elasticsearch.client.transport.TransportClient;
import zhongfucheng.exception.InitException;
import zhongfucheng.exception.SysException;
import zhongfucheng.utils.ElasticsearchUtils;
import zhongfucheng.utils.EsUtils;
import zhongfucheng.utils.FreeMarkerUtils;
import zhongfucheng.utils.ReadPropertiesUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ozc on 2017/11/1.
 */
public class ProjectListener implements ServletContextListener {



    /**
     * 当项目启动的时候，我们就根据模版生成我们的index页面
     *
     * 与Elasticsearch连接、如果index不存在则创建
     *
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {
            //根据模版生成我们的index页面
            FreeMarkerUtils markerUtils = new FreeMarkerUtils();
            Map map = new HashMap();
            map.put("path", ReadPropertiesUtil.readProp("projectPath"));
            markerUtils.ouputFile("index.ftl", "index.html", map);

            //如果在Elasticsearch没有索引、则创建
            TransportClient client = EsUtils.getEsClient();
            if (!ElasticsearchUtils.isIndexExists(client, EsUtils.INDEX_NAME)) {
                ElasticsearchUtils.createIndex(client, EsUtils.INDEX_NAME,EsUtils.TYPE_NAME);
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                throw new InitException("项目初始化异常");
            } catch (InitException e1) {
                e1.printStackTrace();
            }
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
