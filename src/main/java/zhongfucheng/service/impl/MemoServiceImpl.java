package zhongfucheng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongfucheng.entity.Comment;
import zhongfucheng.entity.Memo;
import zhongfucheng.service.CommentService;
import zhongfucheng.service.MemoService;

import java.util.List;


/**
 * Created by ozc on 2017/10/25.
 */

@Service
public class MemoServiceImpl extends BaseServiceImpl<Memo> implements MemoService {


    @Autowired
    private EmailService emailService;

    public List<Memo> queryAllMemo() {
        return memoMapper.queryAllMemo();
    }

    public List<Memo> queryMemoById(String userId) {
        return memoMapper.queryMemoById(userId);
    }


    public void doMemo(Memo memo) {


    }


}
