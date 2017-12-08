package zhongfucheng.service.impl;

import org.springframework.stereotype.Service;
import zhongfucheng.entity.Memo;
import zhongfucheng.service.MemoService;

import java.util.List;


/**
 * 备忘录业务类
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */

@Service
public class MemoServiceImpl extends BaseServiceImpl<Memo> implements MemoService {


    /**
     * @deprecated 测试使用的
     * @return
     */
    public List<Memo> queryAllMemo() {
        return memoMapper.queryAllMemo();
    }

    /**
     * 获取当前用户备忘录的信息
     * @param userId
     * @return
     */
    public List<Memo> queryMemoById(String userId) {
        return memoMapper.queryMemoById(userId);
    }




}
