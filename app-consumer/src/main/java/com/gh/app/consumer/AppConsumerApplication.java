package com.gh.app.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.gh.app.consumer.feignService")
@SpringBootApplication(scanBasePackages = {"com.gh"}, exclude = DataSourceAutoConfiguration.class)
public class AppConsumerApplication
{
    public static void main( String[] args )
    {
    	SpringApplication.run(AppConsumerApplication.class, args);
    }
}
