package com.hhb.concurrency;


import com.hhb.concurrency.example.threadLocal.RequestHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: huanghongbo
 * @Date: 2019-06-16 08:39
 * @Description:
 */
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.err.println("线程ID： " + Thread.currentThread().getId() + ", 访问路径： " + httpServletRequest.getRequestURL());
        RequestHolder.add(Thread.currentThread().getId());
        // 上面是请求被拦截，下面这句是请求经过上面的拦截后，放行
        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
