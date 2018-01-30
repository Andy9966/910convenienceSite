package zhongfucheng.utils;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.shield.ShieldPlugin;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建Client对象连接Elasticsearch
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public class EsUtilsPro {

    public static final String INDEX_NAME = "site";
    public static final String TYPE_NAME = "favorites";

    //默认每页取10条数据
    public static final int PAGE_SIZE = 5;



    static Map<String, String> m = new HashMap<String, String>();
    // 设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中，
    static Settings settings = Settings.builder().put(m).put("cluster.name", "xxxxx").put("client.transport.sniff", true).put("shield.user", "xxxx:xxxx").build();


    // 创建私有对象
    private static TransportClient client;

    static {
        try {
            client = TransportClient.builder()
                    .addPlugin(ShieldPlugin.class)
                    .settings(settings).build();
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 取得实例
    public static synchronized TransportClient getTransportClient() {
        return client;
    }


    public static String getIndexName() {
        return INDEX_NAME;
    }

    public static String getTypeName() {
        return TYPE_NAME;
    }

}
