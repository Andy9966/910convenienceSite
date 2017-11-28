package zhongfucheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zhongfucheng.exception.FavoritesException;
import zhongfucheng.utils.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/11/6.
 */
@Controller
@RequestMapping("/favorites")
public class FavoritesController extends BaseController {

    /**
     * 添加网站
     *
     * @param userId
     * @param webSiteAddr
     * @param webSiteName
     * @param writer
     * @throws FavoritesException
     */
    @RequestMapping("/addSite.do")
    public void addSite(String userId, String webSiteAddr, String webSiteName, PrintWriter writer) throws FavoritesException {


        //当网站地址和网站名都不为null的时候才能添加
        try {
            if (StringUtils.isNotBlank(webSiteAddr, webSiteName)) {
                String json = WebUtils.String2JSON(userId, webSiteAddr, webSiteName);

                String result = ElasticsearchUtils.insertIndexData(EsUtilsPro.getTransportClient(), EsUtils.INDEX_NAME, EsUtils.TYPE_NAME, json, webSiteName,userId);
                writer.write(result);
            } else {
                writer.write("fail");
                return ;
            }


        } catch (IOException e) {
            writer.write("fail");
            throw new FavoritesException("转换JSON失败了/添加网站失败了！");
        }
    }


    /**
     * 根据网站命名查询
     *
     * @param condition
     * @throws UnknownHostException
     */
    @RequestMapping("/querySiteByCondition.do")
    @ResponseBody
    public List<String> querySiteByCondition(String condition,String userId) throws UnknownHostException {
        List<String> list = ElasticsearchUtils.queryIndexByCondition(EsUtilsPro.getTransportClient(), EsUtilsPro.INDEX_NAME, EsUtilsPro.TYPE_NAME, condition, userId);

        return list;
    }

    /**
     * 根据userId查询
     *
     * @throws UnknownHostException
     */
    @RequestMapping("/querySiteById.do")
    @ResponseBody
    public Map<String, String> querySiteById(String userId, Integer currentPage) throws UnknownHostException {

        if (currentPage == null) {
            currentPage = 1;
        }
        Map<String, String> map = ElasticsearchUtils.queryIndexById(EsUtilsPro.getTransportClient(), EsUtilsPro.INDEX_NAME, EsUtilsPro.TYPE_NAME, userId, currentPage);


        return map;
    }

    /**
     * 根据索引id删除
     *
     * @param indexId
     * @return
     * @throws UnknownHostException
     */
    @RequestMapping("/deleteSiteById.do")
    public String deleteSiteById(String indexId) throws UnknownHostException {

        String s = ElasticsearchUtils.deleteIndexData(EsUtilsPro.getTransportClient(), EsUtilsPro.INDEX_NAME, EsUtilsPro.TYPE_NAME, indexId);

        if (StringUtils.equalsIgnoreCase(s, "success")) {
            return "redirect:/goURL/favorites/toFavorites.do";
        }
        return null;
    }


    /**
     * 根据条件查询Elasticsearch中的数据（自动补全）
     *
     * @param condition
     * @throws UnknownHostException
     */
    @RequestMapping("/querySiteCompletion.do")
    @ResponseBody
    public List<String> querySiteCompletion(String condition,String userId) throws UnknownHostException {

        return ElasticsearchUtils.queryIndexByConditionCompletion(EsUtilsPro.getTransportClient(), EsUtilsPro.INDEX_NAME, condition,userId);

    }
}
