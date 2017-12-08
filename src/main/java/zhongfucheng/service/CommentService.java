package zhongfucheng.service;

import zhongfucheng.entity.Comment;

import java.util.List;

/**
 * 评论业务接口
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public interface CommentService extends BaseService<Comment> {
    /**
     * 查询所有的评论数据
     *
     * @return
     */
    List<Comment> queryAllComment();

}
