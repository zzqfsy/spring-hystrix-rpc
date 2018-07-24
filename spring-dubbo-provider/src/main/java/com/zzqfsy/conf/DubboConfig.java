package com.zzqfsy.conf;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.zzqfsy.conf.properties.DubboProperties;
import com.zzqfsy.conf.properties.ZookeeperProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zzqfsy
 * @Description: Dubbo配置中心
 * @Date: Created in 22:03 2018/7/24
 * @Modified By:
 **/
@Configuration
public class DubboConfig {
    public static final String ProtocolName = "dubbo";

    @Autowired
    private ZookeeperProperties zookeeperProperties;
    @Autowired
    private DubboProperties dubboProperties;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("zzqfsyy-message-provider");
        return applicationConfig;
    }

    @Bean(name = DubboConfig.ProtocolName)
    public ProtocolConfig dubboProtocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(dubboProperties.getProviderName());
        protocolConfig.setPort(dubboProperties.getProviderPort());
        protocolConfig.setThreads(dubboProperties.getProviderThreadNum());
        return protocolConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://" + zookeeperProperties.getAddress()+ ":" + zookeeperProperties.getPort());
        registryConfig.setClient("curator");
        return registryConfig;
    }
}
