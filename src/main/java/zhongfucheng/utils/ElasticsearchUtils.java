package zhongfucheng.utils;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;



/**
 * Elasticsearch管理器类
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public class ElasticsearchUtils {

    /**
     * 根据索引名判断该索引库是否存在
     *
     * @param client
     * @param indexName
     * @return
     */
    public static boolean isIndexExists(TransportClient client, String indexName) {
        boolean flag = false;

        IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indexName);

        //下面可判断多个索引名称是否存在
        //IndicesExistsResponse indexResponse = client.admin().indices().prepareExists("blog","blog1").execute().actionGet();

        IndicesExistsResponse inExistsResponse = client.admin().indices()
                .exists(inExistsRequest).actionGet();
        if (inExistsResponse.isExists()) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }


    /**
     * 创建索引、指定其mapping（也就是关系型数据库的类型）
     *
     * @param client
     * @param indexName
     * @param type
     */
    public static void createIndex(TransportClient client, String indexName, String type) {

        //创建索引
        CreateIndexResponse response = client.admin().indices().prepareCreate(indexName).execute().actionGet();
        if (response.isAcknowledged()) {
            System.out.println("create index successfully！");
            //设置mapping
            try {
                XContentBuilder builder =
                        jsonBuilder()
                                .startObject()
                                //这是类型
                                .startObject(type)
                                //这是属性-----------
                                .startObject("properties")
                                .startObject("userId")
                                .field("type", "string")
                                .endObject()
                                .startObject("webSiteName")
                                .field("type", "string")
                                /*.field("analyzer", "ik_max_word")
                                .field("index", "analyzed")*/
                                .endObject()
                                .startObject("webSiteAddr")
                                .field("type", "string")
                                .endObject()
                                //自动补全属性--------
                                .startObject("suggestName")
                                .field("type", "completion")
                                .field("analyzer", "standard")
                                .field("payloads", "true")
                                .endObject()
                                //自动补全属性结束--------
                                .endObject()
                                //属性结束------
                                .endObject()
                                //类型结束-------
                                .endObject();

                PutMappingRequest mapping = Requests.putMappingRequest(indexName).type(type).source(builder);
                client.admin().indices().putMapping(mapping).actionGet();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                EsUtils.closeClient();
            }
        } else {
            System.out.println("Fail to create index!");
        }
    }


    /**
     * 插入数据进索引库中，在插入之前先判断该“命名”索引是否存在
     *
     * @param client
     * @param indexName
     * @param type
     * @param json
     * @param webSiteName
     * @return
     */
    public static String insertIndexData(TransportClient client, String indexName, String type, String json, String webSiteName, String userId) {


        //在插入之前、查看一下有没有该数据、如果有就不允许插入了【本站只允许命名是唯一的】
        List<String> list = queryIndexByCondition(client, indexName, type, webSiteName, userId);

        if (list != null && list.size() > 0) {
            return "hasWebSiteName";
        } else {
            client.prepareIndex(indexName, type).setSource(json).get();
            return "success";
        }


    }

    public static String deleteIndexData(TransportClient client, String indexName, String type, String indexId) {


        //根据查询出来的数据进行删除
        DeleteResponse response = client.prepareDelete(indexName, type, indexId).execute().actionGet();

        if (response.isFound()) {
            return "success";
        } else {
            return "fail";
        }
    }


    /**
     * 根据“命名“查询对应的索引记录
     *
     * @param client
     * @param indexName
     * @param type
     * @param webSiteName
     */
    public static List<String> queryIndexByCondition(TransportClient client, String indexName, String type, String webSiteName, String userId) {

        //对UUID进行截取、比较最前面的几位字符就足够了
        userId = userId.substring(0, 8);

        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.matchQuery("webSiteName", webSiteName));
        query.must(QueryBuilders.matchQuery("userId", userId));

        //接收返回的数据
        List<String> list = new ArrayList<String>();

        SearchResponse response = client.prepareSearch(indexName).setTypes(type).setQuery(query).execute()
                .actionGet();
        SearchHits hits = response.getHits();


        //返回一个最高匹配那个对象就好了。
        if (hits.totalHits() > 0) {
            for (SearchHit hit : hits) {
                list.add(hit.getSourceAsString());
            }
        }


        return list;

    }


    /**
     * 根据用户id查询出索引记录、并对其进行分页
     *
     * @param client
     * @param indexName
     * @param type
     * @param userId
     * @param currentPage
     * @return
     */
    public static Map<String, String> queryIndexById(TransportClient client, String indexName, String type, String userId, Integer currentPage) {


        //对UUID进行截取、比较最前面的几位字符就足够了
        userId = userId.substring(0, 8);

        QueryBuilder query = QueryBuilders.termQuery("userId", userId);

        //接收返回的数据
        Map<String, String> map = new HashMap();

        //计算出开始取的页数
        int startIndex = (currentPage - 1) * EsUtilsPro.PAGE_SIZE;

        //查询出总记录数
        Long totalRecordCount = ElasticsearchUtils.queryTotalCount(client, indexName, type, userId);

        //查询出总页数
        Long totalPageCount = totalRecordCount % EsUtilsPro.PAGE_SIZE == 0 ? totalRecordCount / EsUtilsPro.PAGE_SIZE : totalRecordCount / EsUtilsPro.PAGE_SIZE + 1;

        SearchResponse response = client.prepareSearch(indexName).setTypes(type).setQuery(query).setFrom(startIndex).setSize(EsUtilsPro.PAGE_SIZE).execute().actionGet();


        SearchHits hits = response.getHits();


        //返回一个最高匹配那个对象就好了。
        if (hits.totalHits() > 0) {
            for (SearchHit hit : hits) {
                map.put(hit.getId(), hit.getSourceAsString());
            }
        }

        map.put("currentPage", String.valueOf(currentPage));
        map.put("totalRecordCount", String.valueOf(totalRecordCount));
        map.put("totalPageCount", String.valueOf(totalPageCount));

        return map;

    }

    /**
     * 根据“命名”查询出数据（自动补全）
     *
     * @param client
     * @param indexName
     * @param condition 查询条件
     */
    public static List<String> queryIndexByConditionCompletion(TransportClient client, String indexName, String condition, String userId) {

        String field = "suggestName";


        //对UUID进行截取、比较最前面的几位字符就足够了
        userId = userId.substring(0, 8);


        //先查询该用户的索引结果再与提示字段的数据进行匹配
        SearchRequestBuilder req = client.prepareSearch(indexName);
        CompletionSuggestionBuilder csfb = new CompletionSuggestionBuilder(field).field(field).text(condition).size(100);
        req.addSuggestion(csfb);
        req.setQuery(QueryBuilders.termQuery("userId", userId));
        SearchResponse suggestResponse = req.execute().actionGet();


        //遍历返回的结果、装载到集合中
        List<String> lists = new ArrayList<String>();

        //获取自动补齐的数据
        List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> results = suggestResponse.getSuggest().getSuggestion(field).getEntries();

        //获取以Id查询出来的数据，用户的数据与自动补齐的数据来进行对比。如果相同，则给浏览器显示补齐
        if (suggestResponse.getHits().totalHits() > 0) {

            //获取用户的索引数据
            for (SearchHit hitFields : suggestResponse.getHits()) {
                Map<String, Object> source = hitFields.getSource();
                String webSiteName = (String) source.get("webSiteName");

                //获取以条件搜索出来的suggestion所有数据
                for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> op : results) {
                    List<? extends Suggest.Suggestion.Entry.Option> options = op.getOptions();
                    for (Suggest.Suggestion.Entry.Option pp : options) {
                        if (webSiteName.equals(pp.getText().toString())) {
                            lists.add(pp.getText().toString());
                        }
                    }
                }
            }
        }
        return lists;
    }


    /**
     * 根据用户id查询该用户拥有的总记录数
     *
     * @param client
     * @param indexName
     * @param type
     * @param userId
     * @return
     */
    public static Long queryTotalCount(TransportClient client, String indexName, String type, String userId) {

        //对UUID进行截取、比较最前面的几位字符就足够了
        userId = userId.substring(0, 8);

        QueryBuilder query = QueryBuilders.matchQuery("userId", userId);

        SearchResponse response = client.prepareSearch(indexName).setTypes(type).setQuery(query).execute().actionGet();

        return response.getHits().totalHits();

    }


}
