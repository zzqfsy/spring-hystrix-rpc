package com.zzqfsy.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 10:56 2018/7/24
 * @Modified By:
 **/
public class RPCHystrixFilter implements Filter {

    @Override
    public Result invoke(Invoker invoker, Invocation invocation) throws RpcException {
        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invocation);
        return command.execute();
    }

}