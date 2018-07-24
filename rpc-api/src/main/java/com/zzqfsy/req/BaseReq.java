package com.zzqfsy.req;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: zzqfsy
 * @Description: 请求基类
 * @Date: Created in 17:30 2018/5/4
 * @Modified By:
 **/
public class BaseReq implements Serializable {
    /**
     * 时间戳，精确到毫秒
     */
    @NotNull
    private Long timestamp;
    /**
     * 请求签名
     */
    @NotEmpty
    private String sign;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean checkTimestamp(){
        return true;
    }

    public boolean checkSign(){
        return true;
    }
}