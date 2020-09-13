
package com.gh.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.gray.common.threadLocal.PassParameters;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 灰度请求前置处理
 */
@Slf4j
public class GrayPreFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(GrayPreFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 传递给后续微服务
        String v = ctx.getRequest().getParameter("v");
        if (ctx.getRequest().getHeader("v") != null) {
            v = ctx.getRequest().getHeader("v");
        }
        String version = v;
        if (v != null) {
            ctx.addZuulRequestHeader("version", version);
            Map<String, String> map = new HashMap<String, String>();
            map.put("version", version);
            PassParameters.set(map);
        }
        return null;
    }
}
