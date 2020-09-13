package com.gh.app.consumer.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gh.app.consumer.feignService.HelloRemote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.Map;

@RestController
@Slf4j
public class ApiController {

    @Autowired
    Environment env;
    @Autowired
    private HelloRemote helloRemote;

    @GetMapping("/testService")
    public String get(){
        return "hellow consumer";
    }

    @GetMapping(value = "/get")
    public Map<String, String> testGet(@RequestParam(value = "version", required = false) String version) {
        return ImmutableMap.of("consumer", "success.", "version", StringUtils.defaultIfEmpty(version, ""), "serverPort", env.getProperty("server.port"));
    }

    @GetMapping("/hello-provider")
    public String helloProvider(){
        return helloRemote.printPort();
    }

}
