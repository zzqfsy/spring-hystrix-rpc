package com.zzqfsy.req;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: zzqfsy
 * @Description: 消息请求类
 * @Date: Created in 17:30 2018/5/4
 * @Modified By:
 **/
public class MessageReq extends BaseReq {
    @NotEmpty
    private String recipient;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String message;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
