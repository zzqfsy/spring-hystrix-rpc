package com.zzqfsy.conf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 11:09 2018/7/23
 * @Modified By:
 **/
@Validated
@Component
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {

    private String providerName;
    private Integer providerPort;
    private Integer providerThreadNum;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getProviderPort() {
        return providerPort;
    }

    public void setProviderPort(Integer providerPort) {
        this.providerPort = providerPort;
    }

    public Integer getProviderThreadNum() {
        return providerThreadNum;
    }

    public void setProviderThreadNum(Integer providerThreadNum) {
        this.providerThreadNum = providerThreadNum;
    }
}
