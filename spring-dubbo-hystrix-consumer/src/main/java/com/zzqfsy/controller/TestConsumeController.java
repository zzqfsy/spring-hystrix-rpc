package com.zzqfsy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.zzqfsy.req.MessageReq;
import com.zzqfsy.resp.BaseResp;
import com.zzqfsy.rpc.IMessageFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 22:34 2018/7/24
 * @Modified By:
 **/
@RestController
public class TestConsumeController {

    @RequestMapping(value = "/test/bootstarp", method = RequestMethod.GET)
    public String testbootstarp() {
        return "success";
    }

    @RequestMapping(value = "/test/bootstarp",method = RequestMethod.HEAD)
    public String testbootstarp2() {
        return "success";
    }

    @Reference(version = "1.0.0")
    private IMessageFacade iMessageFacade;

    @RequestMapping(value = "/test/message", method = RequestMethod.GET)
    public BaseResp testMessage() throws IOException {
        MessageReq messageReq = new MessageReq();
        messageReq.setRecipient("张三");
        messageReq.setSubject("测试");
        messageReq.setMessage("我只是测试一下！");
        BaseResp baseResp = iMessageFacade.send(messageReq);
        System.out.println("testMessage: " + JSONObject.toJSONString(baseResp));
        return baseResp;
    }
}
