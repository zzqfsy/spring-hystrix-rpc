package com.zzqfsy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zzqfsy.proxy.MessageProxy;
import com.zzqfsy.req.MessageReq;
import com.zzqfsy.resp.BaseResp;
import com.zzqfsy.resp.MessageResp;
import com.zzqfsy.rpc.IMessageFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 22:34 2018/7/24
 * @Modified By:
 **/
@RestController
public class TestConsumeController {
    private Logger logger = LoggerFactory.getLogger(TestConsumeController.class);

    @RequestMapping(value = "/test/bootstarp", method = RequestMethod.GET)
    public String testbootstarp() {
        return "success";
    }

    @RequestMapping(value = "/test/bootstarp", method = RequestMethod.HEAD)
    public String testbootstarp2() {
        return "success";
    }

    @Autowired
    private MessageProxy messageProxy;

    @RequestMapping(value = "/test/message", method = RequestMethod.GET)
    public BaseResp testMessage() throws IOException {
        MessageReq messageReq = new MessageReq();
        messageReq.setRecipient("张三");
        messageReq.setSubject("测试");
        messageReq.setMessage("我只是测试一下！");
        BaseResp baseResp = messageProxy.send(messageReq);
        logger.info("testMessage: " + JSONObject.toJSONString(baseResp));
        return baseResp;
    }

    @RequestMapping(value = "/test/message/timeout/hystrix", method = RequestMethod.GET)
    public BaseResp testMessageTimeoutHystrix() throws IOException {
        MessageReq messageReq = new MessageReq();
        messageReq.setRecipient("张三");
        messageReq.setSubject("测试");
        messageReq.setMessage("我只是测试一下！");
        BaseResp baseResp = messageProxy.sendTimeoutHystrix(messageReq);
        logger.info("testMessageTimeout: " + JSONObject.toJSONString(baseResp));
        return baseResp;
    }

    @RequestMapping(value = "/test/message/timeout", method = RequestMethod.GET)
    public BaseResp testMessageTimeout() throws IOException {
        MessageReq messageReq = new MessageReq();
        messageReq.setRecipient("张三");
        messageReq.setSubject("测试");
        messageReq.setMessage("我只是测试一下！");
        BaseResp baseResp = messageProxy.sendTimeoutNoHystrix(messageReq);
        logger.info("testMessageTimeout: " + JSONObject.toJSONString(baseResp));
        return baseResp;
    }

    @RequestMapping(value = "/test/message/exception", method = RequestMethod.GET)
    public BaseResp testMessageException() throws IOException {
        MessageReq messageReq = new MessageReq();
        messageReq.setRecipient("张三");
        messageReq.setSubject("测试");
        messageReq.setMessage("我只是测试一下！");
        BaseResp baseResp = messageProxy.sendException(messageReq);
        logger.info("testMessageException: " + JSONObject.toJSONString(baseResp));
        return baseResp;
    }
}
