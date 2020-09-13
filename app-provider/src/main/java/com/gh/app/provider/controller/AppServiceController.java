package com.gh.app.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AppServiceController {

    @Autowired
    Environment env;

    @RequestMapping("/api")
    public String printPort() {
        return "provider:" + env.getProperty("server.port");
    }
}
