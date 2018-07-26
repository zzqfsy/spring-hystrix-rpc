package com.zzqfsy.service;

import com.alibaba.fastjson.JSONObject;
import com.zzqfsy.req.MessageReq;
import com.zzqfsy.resp.MessageResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author: zzqfsy
 * @Description: 消息业务
 * @Date: Created in 21:55 2018/7/24
 * @Modified By:
 **/
@Service
public class MessageService {
    private final Logger logger = LoggerFactory.getLogger(MessageService.class);

    /**
     * 发送消息
     * @param messageReq
     * @return
     */
    public MessageResp send(MessageReq messageReq){
        logger.info("send message: " + JSONObject.toJSONString(messageReq));
        return new MessageResp(LocalTime.now().toString());
    }

    /**
     * 发送延迟消息
     * @param messageReq
     * @param millis
     * @return
     */
    public MessageResp sendTimeout(MessageReq messageReq, Long millis){
        logger.info("send message: " + JSONObject.toJSONString(messageReq));
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new MessageResp("success");
    }

    /**
     * 发送异常消息
     * @param messageReq
     * @return
     */
    public MessageResp sendException(MessageReq messageReq){
        logger.info("sendException message: " + JSONObject.toJSONString(messageReq));
        throw new RuntimeException("customRuntimeException");
    }
}
