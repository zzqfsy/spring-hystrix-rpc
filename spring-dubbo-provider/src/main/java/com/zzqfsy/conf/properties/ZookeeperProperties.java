package com.zzqfsy.conf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 10:58 2018/7/23
 * @Modified By:
 **/
@Validated
@Component
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperProperties {

    private String address;
    private String port;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
