package com.gh.zuul;


import com.gh.zuul.filter.GrayPostFilter;
import com.gh.zuul.filter.GrayPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = {"com.gh"}, exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public GrayPreFilter grayPreFilter() {
        return new GrayPreFilter();
    }
    @Bean
    public GrayPostFilter grayPostFilter() {
        return new GrayPostFilter();
    }

}
