package zhongfucheng.entity;

import java.util.Date;

/**
 * 存储备忘录的信息
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 */
public class Memo {
    private String memoId;

    private Date editTime;

    private Date sendTime;

    private String userId;

    private Integer state;

    private String memoContent;

    //已经发送邮件和未发送邮件常量
    public static final Integer ALREADY_SEND = 1;
    public  static final Integer NO_SEND = 0;

    public String getMemoId() {
        return memoId;
    }

    public void setMemoId(String memoId) {
        this.memoId = memoId;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }
}