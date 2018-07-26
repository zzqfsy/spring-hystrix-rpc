package com.zzqfsy.proxy;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zzqfsy.req.MessageReq;
import com.zzqfsy.resp.BaseResp;
import com.zzqfsy.rpc.IMessageFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

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

    @HystrixCommand(
            groupKey = "MessageProxy",
            commandKey = "send",
            commandProperties = {
                    @HystrixProperty(name = "execution.timeout.enabled", value = "false"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "15"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
            },
            fallbackMethod = "fallback")
    public BaseResp send(MessageReq messageReq){
        return iMessageFacade.send(messageReq);
    }

    // HystrixCommandAspect
    // https://github.com/Netflix/Hystrix/wiki/Configuration
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
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100"),   // 设置调用线程产生的HystrixCommand.getFallback()方法的允许最大请求数目。|default: 10
            },
            threadPoolProperties = {
                    //HystrixThreadPoolProperties
                    @HystrixProperty(name = "coreSize", value = "15"),  //线程池核心数|default:10 超过队列fallback
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),  //队列长度|default:-1(SynchronousQueue)
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1"),   // 设置存活时间，单位分钟| 默认值：1|如果coreSize小于maximumSize，那么该属性控制一个线程从实用完成到被释放的时间。
//                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),   //队满拒绝服务阈值|default:5|此值生效优先于队满, maxQueueSize设置为-1，该属性不可用。
                    // Metrics统计属性
                    //@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),   // 设置滑动统计的桶数量。默认10。metrics.rollingStats.timeInMilliseconds必须能被这个值整除。
                    //@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")  //窗口维持时间|default:10000
            },
            // HystrixThreadPoolKey threadPoolKey =
            fallbackMethod = "fallback")
    public BaseResp sendTimeout(MessageReq messageReq){
        return iMessageFacade.sendTimeout(messageReq, 5000L);
    }

    public BaseResp sendException(MessageReq messageReq){
        return iMessageFacade.sendException(messageReq);
    }

    public BaseResp fallback(MessageReq messageReq, Throwable e){
        logger.error("fallback", e);
        return BaseResp.getFailInstance("故障回退:" + LocalTime.now().toString());
    }

}
