package com.zzqfsy.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzqfsy.conf.DubboConfig;
import com.zzqfsy.req.MessageReq;
import com.zzqfsy.resp.BaseResp;
import com.zzqfsy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zzqfsy
 * @Description: 消息DUBBO RPC接口
 * @Date: Created in 21:55 2018/7/24
 * @Modified By:
 **/
@Service(timeout = 3000, cluster = "failover", loadbalance = "roundrobin", protocol = {DubboConfig.ProtocolName}, version = "1.0.0")
public class MessageFacade implements IMessageFacade {

    @Autowired
    private MessageService messageService;

    @Override
    public BaseResp send(MessageReq messageReq) {
        return BaseResp.getInstance(messageService.send(messageReq));
    }

    @Override
    public BaseResp sendTimeout(MessageReq messageReq, Long millis) {
        return BaseResp.getInstance(messageService.sendTimeout(messageReq, millis));
    }

    @Override
    public BaseResp sendException(MessageReq messageReq) {
        return BaseResp.getInstance(messageService.sendException(messageReq));
    }
}
