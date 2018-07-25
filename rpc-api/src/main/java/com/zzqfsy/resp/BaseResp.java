package com.zzqfsy.resp;

import java.io.Serializable;

/**
 * @Author: zzqfsy
 * @Description: 返回基类
 * @Date: Created in 17:30 2018/5/4
 * @Modified By:
 **/
public class BaseResp<T> implements Serializable {
    private String code;

    private T result;

    private Integer errorType;

    private String errorMsg;

    private boolean isSuccess = true;

    public static BaseResp getInstance(){
        return new BaseResp();
    }

    public static BaseResp getInstance(Object result){
        return new BaseResp(result);
    }

    public static BaseResp getFailInstance(String errorMsg){
        return new BaseResp("100").setErrorMsgExtend(errorMsg);
    }

    private BaseResp() {
        this.code = "0";
    }

    private BaseResp(T result) {
        this.code = "0";
        this.result = result;
    }

    private BaseResp(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public BaseResp setResultExtend(T result) {
        this.result = result;
        return this;
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BaseResp setErrorMsgExtend(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public boolean isSuccess() {
        return isSuccess & "0".equals(code);
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
