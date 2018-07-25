package com.zzqfsy.proxy;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zzqfsy.req.BaseReq;
import com.zzqfsy.req.MessageReq;
import com.zzqfsy.resp.BaseResp;
import com.zzqfsy.rpc.IMessageFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 15:32 2018/7/25
 * @Modified By:
 **/
@Component
public class MessageProxy {
    private Logger logger = LoggerFactory.getLogger(MessageProxy.class);

    @Reference(version = "1.0.0")
    private IMessageFacade iMessageFacade;

    public BaseResp send(MessageReq messageReq){
        return iMessageFacade.send(messageReq);
    }

    // HystrixCommandAspect
    @HystrixCommand(
            // HystrixCommandGroupKey
            groupKey = "MessageProxy",
            // HystrixCommandKey
            commandKey = "sendTimeout",
            commandProperties = {
                    //HystrixCommandProperties
                    @HystrixProperty(name = "execution.timeout.enabled", value = "false"),    //执行超时时间|default:1000
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),    //执行超时时间|default:1000
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"), //触发断路最低请求数|default:20
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),    //断路器恢复时间|default:5000
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),   //|触发短路错误率,单位%|default:50
            },
            threadPoolProperties = {
                    //HystrixThreadPoolProperties
                    @HystrixProperty(name = "coreSize", value = "15"),  //线程池核心数|default:10
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),  //队列长度|default:-1(SynchronousQueue)
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),   //队满拒绝服务阈值|default:5|此值生效优先于队满
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),   //队满拒绝服务阈值|default:5|此值生效优先于队满
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),   ////队满拒绝服务阈值|default:5|此值生效优先于队满
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")  //窗口维持时间|默认10000
            },
            // HystrixThreadPoolKey
            //
            fallbackMethod = "fallback")
    public BaseResp sendTimeout(MessageReq messageReq){
        return iMessageFacade.sendTimeout(messageReq, 5000L);
    }

    public BaseResp sendException(MessageReq messageReq){
        return iMessageFacade.sendException(messageReq);
    }

    public BaseResp fallback(MessageReq messageReq, Throwable e){
        logger.error("fallback", e);
        return BaseResp.getFailInstance("故障回退");
    }

}
