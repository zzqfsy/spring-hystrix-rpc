package com.zzqfsy.rpc;

import com.zzqfsy.req.MessageReq;
import com.zzqfsy.resp.BaseResp;

/**
 * @Author: zzqfsy
 * @Description: 消息接口
 * @Date: Created in 17:30 2018/5/4
 * @Modified By:
 **/
public interface IMessageFacade {

    /**
     * 发送消息
     * @param messageReq
     * @return
     */
    BaseResp send(MessageReq messageReq);

    /**
     * 发送消息，等待时间
     * @param messageReq
     * @param millis
     * @return
     */
    BaseResp sendTimeout(MessageReq messageReq, Long millis);

    /**
     * 发送消息，抛出异常
     * @param messageReq
     * @return
     */
    BaseResp sendException(MessageReq messageReq);
}
