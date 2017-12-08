package zhongfucheng.service.impl;

import org.springframework.stereotype.Service;
import zhongfucheng.entity.Comment;
import zhongfucheng.service.CommentService;

import java.util.List;


/**
 * 评论的业务类
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {


    public List<Comment> queryAllComment() {
        return commentMapper.queryAllComment();
    }


}
