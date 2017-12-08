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
 * 个人收藏夹Controller
 * <p>
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
@Controller
@RequestMapping("/favorites")
public class FavoritesController extends BaseController {

    /**
     * 添加网站到索引库中
     *
     * @param userId 用户Id
     * @param webSiteAddr 网站地址
     * @param webSiteName 网站命名
     * @param writer 把结果写给前台，success  Or fail
     * @throws FavoritesException
     */
    @RequestMapping("/addSite.do")
    @ResponseBody
    public void addSite(String userId, String webSiteAddr, String webSiteName, PrintWriter writer) throws FavoritesException {

        try {

            //当网站地址和网站名都不为null的时候才能添加
            if (StringUtils.isNotBlank(webSiteAddr, webSiteName)) {

                String json = WebUtils.String2JSON(userId, webSiteAddr, webSiteName);
                String result = ElasticsearchUtils.insertIndexData(EsUtilsPro.getTransportClient(), EsUtils.INDEX_NAME, EsUtils.TYPE_NAME, json, webSiteName, userId);

                writer.write(result);
            } else {
                writer.write("fail");
                return;
            }

        } catch (IOException e) {
            writer.write("fail");
            throw new FavoritesException("转换JSON失败了/添加网站失败了！");
        }
    }



    /**
     * 根据用户所在的命名查询记录
     * @param condition 网站命名
     * @param userId 用户Id
     * @return 查询返回的集合JSON
     * @throws UnknownHostException
     */
    @RequestMapping("/querySiteByCondition.do")
    @ResponseBody
    public List<String> querySiteByCondition(String condition, String userId) throws UnknownHostException {
        List<String> list = ElasticsearchUtils.queryIndexByCondition(EsUtilsPro.getTransportClient(), EsUtilsPro.INDEX_NAME, EsUtilsPro.TYPE_NAME, condition, userId);

        return list;
    }


    /**
     * 根据用户Id查询索引数据、支持分页
     * @param userId 用户Id
     * @param currentPage 当前页数
     * @return 返回Map类型的JSON
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
     * 根据索引id删除索引
     *
     * @param indexId 索引Id
     * @return 删除成功则重定向到页面上
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
     * 在当前用户下根据条件查询Elasticsearch中的数据（自动补全）
     * @param condition 页面传递进来的命名
     * @param userId 用户Id
     * @return 符合条件的查询数据
     * @throws UnknownHostException
     */
    @RequestMapping("/querySiteCompletion.do")
    @ResponseBody
    public List<String> querySiteCompletion(String condition, String userId) throws UnknownHostException {

        return ElasticsearchUtils.queryIndexByConditionCompletion(EsUtilsPro.getTransportClient(), EsUtilsPro.INDEX_NAME, condition, userId);

    }
}
