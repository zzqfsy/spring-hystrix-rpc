package com.zzqfsy.filter;

import com.alibaba.dubbo.rpc.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 16:17 2018/7/30
 * @Modified By:
 **/
public class HystrixRequestContextFilter implements Filter {

    @Override
    public Result invoke(Invoker invoker, Invocation invocation) throws RpcException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            return invoker.invoke(invocation);
        } finally {
            context.shutdown();
        }
    }
}