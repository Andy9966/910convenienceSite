package zhongfucheng.dao;

import zhongfucheng.entity.Comment;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {


    /**
     * 查询所有的评论数据
     *
     * @return
     */
    List<Comment> queryAllComment();



}