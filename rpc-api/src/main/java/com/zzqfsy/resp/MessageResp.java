package com.zzqfsy.resp;

import java.io.Serializable;

/**
 * @Author: zzqfsy
 * @Description: 消息返回类
 * @Date: Created in 17:30 2018/5/4
 * @Modified By:
 **/
public class MessageResp implements Serializable{

    public MessageResp() {
    }

    public MessageResp(String tip) {
        this.tip = tip;
    }

    private String tip;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
