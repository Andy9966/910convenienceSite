package zhongfucheng.dao;

import zhongfucheng.entity.Comment;

import java.util.List;


/**
 * 评论Mapper
 * <p>
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0

 */
public interface CommentMapper extends BaseMapper<Comment> {


    /**
     * 查询所有的评论数据
     *
     * @return
     */
    List<Comment> queryAllComment();



}