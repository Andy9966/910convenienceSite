package zhongfucheng.service;

import zhongfucheng.entity.Comment;
import zhongfucheng.entity.Memo;

import java.util.List;

/**
 * Created by ozc on 2017/10/25.
 */
public interface MemoService extends BaseService<Memo> {
    /**
     * 查询所有的备忘录数据
     *
     * @return
     */
    List<Memo> queryAllMemo();

    /**
     * 通过用户Id获取备忘录的数据
     * @param userId
     * @return
     */
    List<Memo> queryMemoById(String userId);


    /**
     * 当时间达到了，那么就发邮件提示用户
     */
    void doMemo(Memo memo);


}
