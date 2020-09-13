# SpringCloud-gray
整合Euraka实现灰度发布

在一般情况下，升级服务器端应用，需要将应用源码或程序包上传到服务器，然后停止掉老版本服务，再启动新版本。但是这种简单的发布方式存在两个问题，一方面，在新版本升级过程中，服务是暂时中断的，另一方面，如果新版本有BUG，升级失败，回滚起来也非常麻烦，容易造成更长时间的服务不可用。

什么是灰度发布呢？要想了解这个问题就要先明白什么是灰度。灰度从字面意思理解就是存在于黑与白之间的一个平滑过渡的区域，所以说对于互联网产品来说，上线和未上线就是黑与白之分，而实现未上线功能平稳过渡的一种方式就叫做灰度发布。

互联网产品的几个特点：用户规模大、版本更新频繁。新版本的每次上线，产品都要承受极大的压力，而灰度发布很好的规避了这种风险。

在了解了什么是灰度发布的定义以后，就可以来了解一下灰度发布的具体操作方法了。可以通过很多种形式来抽取一部分用户，比如说选择自己的VIP用户，或者选择一些活跃用户，把这些用户分成两批，其中一批投放A版本，另外一批投放B版本，在投放之前就要对各种可能存在的数据做到收集记录工作，这样才能在投放以后查看两个版本的用户数据反馈，通过大量的数据分析以及调查来确定最后使用哪一个版本来进行投放。

那么，在springcloud的分布式环境中，我们如何来区分用户（版本），如何来指定这部分用户使用不同版本的微服务?这篇文章将会通过实际的例子来说明这个过程。

假设用户发起一个访问，服务的调用路径为：用户--> ZUUL -->app-consumer-->app-provider，那么我们在ZUUL和SERVICE1都需要实现自定义的访问路由。

下面这个设计的重点主要在：

1. 利用threadlocal+feign实现http head中实现版本信息的传递

2. 使用Euraka的元数据，定义我们需要的灰度服务

3. 自定义ribbon的路由规则，根据Euraka的元数据选择服务节点



公共配置：

1. ThreadLocal
    com.gray.common.threadLocal.PassParameters
    
2. AOP拦截请求头
    com.gray.common.aop.ApiRequestAspect

3. 实现自己的GrayMetadataRule
    GrayMetadataRule 将会从Euraka中获取元服务器的信息，并根据这个信息选择服务器
    com.gray.common.core.GrayMetadataRule
    
4. feign 拦截器,将threadlocal中的内容读出并写入请求头,通过这种方式传递版本信息
    com.gray.common.core.DefaultFeignConfig

## ZUUL
增加自定义ZUUL过滤器,拦截后将版本信息放到threadlocal中,在路由的时候,判断当前的版本信息
    com.gh.zuul.filter.GrayPreFilter
    com.gh.zuul.filter.GrayPostFilter

## APP-CONSUMER配置
app-consumer,通过切面获取到版本信息,并将版本信息放入threadlocal中,通过feign再封装到http头,传递到下一层

## 快速开始
    把项目中的eureka-server、zuul启动，
    并启动多实例的app-consumer、app-provider同时给给不同实例配置不同的端口号、版本，如：
    server.port=8802
    #灰度版本号
    eureka.instance.metadata-map.version=2
    与
    server.port=8803
    #灰度版本号
    eureka.instance.metadata-map.version=3
    
    调用http://localhost:8888/app-consumer/get即可实现zuul到app-consumer的灰度
    调用http://localhost:8888/app-consumer/hello-provider即可实现zuul到app-consumer再到app-provider的灰度