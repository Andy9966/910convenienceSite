package zhongfucheng.service.impl;

import org.springframework.stereotype.Service;
import zhongfucheng.entity.Comment;
import zhongfucheng.service.CommentService;

import java.util.List;


/**
 * Created by ozc on 2017/10/25.
 */

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {


    public List<Comment> queryAllComment() {
        return commentMapper.queryAllComment();
    }


}
