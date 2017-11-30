package zhongfucheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zhongfucheng.entity.Comment;
import zhongfucheng.entity.User;

import java.util.*;

/**
 * 关于网站的Controller
 */


@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {


    /**
     * 查询网站的所有评论
     *
     * @return
     */
    @RequestMapping("/queryComment.do")
    @ResponseBody
    public List<Map<String, Object>> queryComment() {


        //评论条数可能不止一个，使用List集合
        List<Map<String, Object>> mapList = new ArrayList();


        List<Comment> comments = commentService.queryAllComment();

        int num = 1;
        for (Comment comment : comments) {

            //将评论的信息存到这里
            Map<String, Object> resultMap = new HashMap<String, Object>();

            String userId = comment.getUserId();
            User user = userService.selectByPrimaryKey(userId);

            resultMap.put("order", num + "楼");
            resultMap.put("nickName", user.getUserNickname());
            resultMap.put("createTime", comment.getCreateTime());
            resultMap.put("content", comment.getContent());

            num++;
            mapList.add(resultMap);

        }

        return mapList;

    }


    /**
     * 查询网站的所有评论
     *
     * @return
     */
    @RequestMapping("/addComment.do")
    @ResponseBody
    public String addComment(String comment, String userId) {

        Comment comment1 = new Comment();

        comment1.setUserId(userId);
        comment1.setCreateTime(new Date());
        comment1.setContent(comment);

        int result = commentService.insert(comment1);

        //插入成功
        if (result > 0) {
            return "success";

        } else {
            return "fail";
        }



    }


}
