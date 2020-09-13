//package com.start.common.core;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
////@Configuration
//@ConditionalOnClass(com.netflix.loadbalancer.ZoneAvoidanceRule.class)
//public class MyRibbonConfiguration implements InitializingBean {
//    @Value("#{'${loadbalanced.services:default}'.split(',')}")
//    private List<String> loadbalancedServices;
//    /**
//     * 默认使用切流量的负载均衡策略
//     */
//    @Value("${ribbon.NFLoadBalancerRuleClassName:default}")
//    private String ribbonLoadBancerRule;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        if (loadbalancedServices != null) {
//            for (String service : loadbalancedServices) {
//                String key = service + ".ribbon.NFLoadBalancerRuleClassName";
//                System.setProperty(key, ribbonLoadBancerRule);
//            }
//        }
//    }
//}