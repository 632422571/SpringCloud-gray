package com.gh.app.consumer.feignService;

import org.springframework.stereotype.Component;

@Component
public class HelloRemoteHystrix implements HelloRemote {

    @Override
    public String printPort() {
        return "Sorry there is a error";
    }
}
