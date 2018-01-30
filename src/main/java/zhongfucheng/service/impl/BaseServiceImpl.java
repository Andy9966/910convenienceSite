package zhongfucheng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import zhongfucheng.dao.BaseMapper;
import zhongfucheng.dao.CommentMapper;
import zhongfucheng.dao.MemoMapper;
import zhongfucheng.dao.UserMapper;
import zhongfucheng.service.BaseService;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * 使用initBaseMapper()将baseMapper实例化，service实现类是什么类型，baseMapper就是什么类型
 * 将所有的Mapper都定义出来，那么子类service就可以直接使用了
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    protected BaseMapper<T> baseMapper;

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected CommentMapper commentMapper;

    @Autowired
    protected MemoMapper memoMapper;


    /**
     * 初始化baseMapper，哪种类型的service实现调用该方法，baseMapper就是那种类型
     *
     * @throws Exception
     */
    @PostConstruct
    private void initBaseMapper() throws Exception {

        //获取泛型的信息
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class) type.getActualTypeArguments()[0];

        //拼接成“泛型”Mapper字符串
        String localField = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1) + "Mapper";

        //通过反射来获取成员变量的值
        Field field = this.getClass().getSuperclass().getDeclaredField(localField);
        Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper");

        //将baseDao来进行实例化
        baseField.set(this, field.get(this));

    }

    public int insert(T entity) {
        return baseMapper.insert(entity);
    }

    public int insertSelective(T entity) {
        return baseMapper.insertSelective(entity);
    }

    public int deleteByPrimaryKey(String id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    public T selectByPrimaryKey(String id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    public int updateByPrimaryKey(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

}
