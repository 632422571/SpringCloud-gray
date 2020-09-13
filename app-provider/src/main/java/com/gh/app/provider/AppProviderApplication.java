package com.gh.app.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.gh"}, exclude = DataSourceAutoConfiguration.class)
public class AppProviderApplication
{
    public static void main( String[] args )
    {
    	SpringApplication.run(AppProviderApplication.class, args);
    }
}
