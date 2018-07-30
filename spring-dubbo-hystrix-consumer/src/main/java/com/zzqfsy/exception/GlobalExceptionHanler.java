package com.zzqfsy.exception;

import com.alibaba.dubbo.rpc.RpcException;
import com.google.common.base.Throwables;
import com.zzqfsy.resp.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zzqfsy
 * @Description: 控制器异常全局捕获
 * @Date: Created in 15:47 2018/7/25
 * @Modified By:
 **/
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHanler {
    private static Logger logger= LoggerFactory.getLogger(GlobalExceptionHanler.class);

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResp errorHandler(HttpServletRequest req, NoHandlerFoundException exception) throws Exception {
        loggerException(exception, false);
        return BaseResp.getFailInstance("请求地址不存在");
    }

    @ExceptionHandler(value = RpcException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResp errorHandler(HttpServletRequest req, RpcException exception) throws Exception {
        loggerException(exception, true);
        return BaseResp.getFailInstance("服务繁忙，请稍等重试");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResp exception(Exception exception, WebRequest request) {
        loggerException(exception, true);
        return BaseResp.getFailInstance(Throwables.getRootCause(exception).getMessage());
    }

    private void loggerException(Exception ex, boolean isImportance){
        logger.error("GlobalExceptionHanler loggerException: ", ex);
    }
}
