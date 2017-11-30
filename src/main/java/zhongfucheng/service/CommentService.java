package zhongfucheng.service;

import zhongfucheng.entity.Comment;

import java.util.List;

/**
 * Created by ozc on 2017/10/25.
 */
public interface CommentService extends BaseService<Comment> {
    /**
     * 查询所有的评论数据
     *
     * @return
     */
    List<Comment> queryAllComment();

}
