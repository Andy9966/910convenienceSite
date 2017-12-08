package zhongfucheng.service;

import zhongfucheng.entity.Comment;
import zhongfucheng.entity.Memo;

import java.util.List;

/**
 * 备忘录接口
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public interface MemoService extends BaseService<Memo> {
    /**
     * 查询所有的备忘录数据，测试使用的
     *
     * @deprecated
     * @return
     */
    List<Memo> queryAllMemo();

    /**
     * 通过用户Id获取备忘录的数据
     * @param userId
     * @return
     */
    List<Memo> queryMemoById(String userId);




}
