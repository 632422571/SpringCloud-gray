package com.gray.common.aop;


import com.gray.common.threadLocal.PassParameters;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Order(Integer.MIN_VALUE + 1)
public class ApiRequestAspect {
    private static Logger logger = LoggerFactory.getLogger(ApiRequestAspect.class);


    @Pointcut("execution(* com..consumer.controller.*.*(..))")
    private void anyMethod() {
    }

    /**
     * 方法调用之前调用
     */
    @Before(value = "anyMethod()")
    public void doBefore(JoinPoint jp) {
        logger.info("开始处理请求信息1111111111！");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Map<String, String> map = new HashMap<>();
        String username = request.getHeader("username");
        String token = request.getHeader("token");
        String version = request.getParameter("version");
        if (version == null) {
            request.getHeader("version");
        }
        map.put("username", username);
        map.put("token", token);
        map.put("version", version);
        //将map放到threadLocal中
        PassParameters.set(map);
    }

    /**
     * 方法之后调用
     */
    @AfterReturning(pointcut = "anyMethod()")
    public void doAfterReturning() {
        PassParameters.remove();
    }

}