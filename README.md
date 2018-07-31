# Spring-Hystrix-rpc
该项目目的是熟悉hystrix特性，展示流控效果

服务依赖
* zookeeper(zookeeper://127.0.0.1:2181)

主要jar依赖
* spring boot 2.0.3.RELEASE
* dubbo 2.6.2
* hystrix 1.5.12

## rpc-api
该模块定义服务的接口
```
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─zzqfsy
    │  │          ├─req
    │  │          ├─resp
    │  │          └─rpc
    │  └─resources
    └─test
        └─java
```

## spring-dubbo-provider
该模块定义Dubbo的服务端
```
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─zzqfsy
    │  │          ├─conf
    │  │          │  └─properties
    │  │          ├─rpc
    │  │          └─service
    │  └─resources
    └─test
        └─java
```

## spring-dubbo-hystrix-consumer
该模块定义Dubbo的消费端，并接入hystrix
```
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─zzqfsy
    │  │          ├─conf
    │  │          │  └─properties
    │  │          ├─controller
    │  │          ├─exception
    │  │          ├─filter
    │  │          └─proxy
    │  └─resources
    │      └─META-INF
    │          └─dubbo
    └─test
        └─java
```

## 流控效果
测试脚本
> ./spring-dubbo-hystrix-consumer/src/main/resources/dubboTest.jmx

压测结果—正常调用—不使用hystrix
![exception_thread_group](https://raw.githubusercontent.com/zzqfsy/spring-hystrix-rpc/master/resource/exception_thread_group.png)

压测结果—异常调用—使用hystrix
![normal_thread_group](https://raw.githubusercontent.com/zzqfsy/spring-hystrix-rpc/master/resource/normal_thread_group.png)

压测结果—超时调用—不使用hystrix
![timeout_thread_group_by_no_hystrix](https://raw.githubusercontent.com/zzqfsy/spring-hystrix-rpc/master/resource/timeout_thread_group_by_no_hystrix.png)

压测结果—超时调用—使用hystrix
![timeout_thread_group_by_hystrix](https://raw.githubusercontent.com/zzqfsy/spring-hystrix-rpc/master/resource/timeout_thread_group_by_hystrix.png)


## 监控
* [standalone-hystrix-dashboard](https://github.com/kennedyoliveira/standalone-hystrix-dashboard)
