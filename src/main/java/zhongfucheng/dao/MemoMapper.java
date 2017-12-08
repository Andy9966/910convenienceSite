package zhongfucheng.dao;

import zhongfucheng.entity.Memo;

import java.util.List;

/**
 * 备忘录Mapper
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public interface MemoMapper extends BaseMapper<Memo>  {


    /**
     * 查询所有的备忘录数据（我在测试的时候使用的）
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