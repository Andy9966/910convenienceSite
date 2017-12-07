package zhongfucheng.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zhongfucheng.entity.Memo;
import zhongfucheng.entity.User;
import zhongfucheng.quartz.MemoJobDetail;
import zhongfucheng.quartz.QuartzManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Memo个人备忘录的Controller
 */

@Controller
@RequestMapping("/memo")
public class MemoController extends BaseController {


    // 日期转换器
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

    }

    /**
     * 通过UserId查询网站的所有评论
     * @param userId
     * @return
     */
    @RequestMapping("/queryAllMemo.do")
    @ResponseBody
    public List<Map<String, Object>> queryAllMemo(String userId) {

        //评论条数可能不止一个，使用List集合
        List<Map<String, Object>> mapList = new ArrayList();

        List<Memo> memos = memoService.queryMemoById(userId);

        for (Memo memo : memos) {

            //将备忘录的信息存到这里
            Map<String, Object> resultMap = new HashMap<String, Object>();

            User user = userService.selectByPrimaryKey(userId);

            resultMap.put("nickName", user.getUserNickname());
            resultMap.put("editTime", memo.getEditTime());
            resultMap.put("email", user.getUserEmail());
            resultMap.put("content", memo.getMemoContent());
            resultMap.put("memoId", memo.getMemoId());


            resultMap.put("sendTime", memo.getSendTime());
            if (memo.getState() == 0) {
                resultMap.put("state", "未发送");
            } else if (memo.getState() == 1) {
                resultMap.put("state", "已发送");
            } else {
                resultMap.put("state", "已发送");

            }
            mapList.add(resultMap);
        }
        return mapList;

    }

    /**
     * 修改评论
     * 同时需要修改触发器的时间
     *
     * @return
     */
    @RequestMapping("/updateMemo.do")
    @ResponseBody
    public String updateMemo(Memo memo) throws ParseException, SchedulerException {

        int result = 0;

        if (memo.getMemoId() != null) {

            result = memoService.updateByPrimaryKeySelective(memo);

            QuartzManager.modifyJobTime(sche,memo);
        }

        if (result > 0) {
            return "success";

        } else {
            return "fail";
        }
    }

    /**
     * 添加备忘录
     *
     * @return
     */
    @RequestMapping("/saveMemo.do")
    public String saveMemo(Memo memo) throws ParseException, InterruptedException, SchedulerException {

        memoService.insert(memo);

        //设置定时器任务
        QuartzManager.addJob(sche, MemoJobDetail.class, memo);

        return "redirect:/goURL/memo/toMemo.do";
    }

    /**
     * 查询网站的所有评论
     *
     * @return
     */
    @RequestMapping("/deleteMemo.do")
    public String deleteMemo(String memoId) throws ParseException, SchedulerException {

        memoService.deleteByPrimaryKey(memoId);


        //把任务也得删除了
        QuartzManager.removeJob(sche, memoId, memoId);

        return "redirect:/goURL/memo/toMemo.do";


    }


}
