package com.gray.common.core;


import com.gray.common.threadLocal.PassParameters;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.util.Map;


@ConditionalOnClass(Feign.class)
public class DefaultFeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, String> map = PassParameters.get();

        if (map != null) {
            String username = map.get("username");
            if (StringUtils.isNotEmpty(username)) {
                requestTemplate.header("username", username);
            }
            String token = map.get("token");
            if (StringUtils.isNotEmpty(token)) {
                requestTemplate.header("token", token);
            }

            String version = map.get("version");
            if (StringUtils.isNotEmpty(version)) {
                requestTemplate.header("version", version);
            }
        }
    }

}
