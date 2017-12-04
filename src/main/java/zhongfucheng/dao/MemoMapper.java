package zhongfucheng.dao;

import zhongfucheng.entity.Memo;

import java.util.List;

public interface MemoMapper extends BaseMapper<Memo>  {

    List<Memo> queryAllMemo();

    /**
     * 通过用户Id获取备忘录的数据
     * @param userId
     * @return
     */
    List<Memo> queryMemoById(String userId);
}