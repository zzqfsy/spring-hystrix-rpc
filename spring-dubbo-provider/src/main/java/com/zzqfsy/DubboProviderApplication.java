package com.zzqfsy;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 22:20 2018/7/24
 * @Modified By:
 **/
@SpringBootApplication
@DubboComponentScan(basePackages = "com.zzqfsy.rpc")
public class DubboProviderApplication {

    public static void main(String[] args) throws Exception {
        System.setProperty("dubbo.application.logger","slf4j");

        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
