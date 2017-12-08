package zhongfucheng.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 日期转换器，我在Controller内部使用注解的方式取代了。这种方式要去配置XML文件。
 * <p>
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 * @deprecated
 */
@Component
public class DateConverter implements Converter<String, Date> {

    public Date convert(String source) {
        try {
            //进行日期转换
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
