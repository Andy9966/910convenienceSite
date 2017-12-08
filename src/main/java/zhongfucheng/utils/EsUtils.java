package zhongfucheng.utils;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.shield.ShieldPlugin;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 * @deprecated 已被EsUtilsPro替代
 */
public class EsUtils {

    public static final String INDEX_NAME = "site";
    public static final String TYPE_NAME = "favorites";

    private static TransportClient client;


    public static TransportClient  getEsClient(){

        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "zhongfucheng_123")
                .put("shield.user", "zhongfucheng:zhongfucheng")
                .build();
        TransportClient client = TransportClient.builder()
                .addPlugin(ShieldPlugin.class)
                .settings(settings).build();
        try {
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void closeClient(){
        if(client!=null)
            client.close();
    }

    public static String getIndexName() {
        return INDEX_NAME;
    }
    public static String getTypeName() {
        return TYPE_NAME;
    }

}
