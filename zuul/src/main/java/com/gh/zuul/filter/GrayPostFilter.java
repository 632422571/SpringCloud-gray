
package com.gh.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.gray.common.threadLocal.PassParameters;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 灰度请求后置处理
 */
@Slf4j
public class GrayPostFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(GrayPostFilter.class);
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        PassParameters.remove();
        return null;
    }
}
