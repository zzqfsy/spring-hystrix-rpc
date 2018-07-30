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
