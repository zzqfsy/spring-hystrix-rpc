package com.zzqfsy.conf;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zzqfsy
 * @Description:
 * @Date: Created in 9:49 2018/7/25
 * @Modified By:
 **/
@Configuration
public class WebMvcConfig {

    @Bean
    public ServletRegistrationBean loggingFilterRegistrationBean(){
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
    }
}