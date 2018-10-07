package com.cloudy.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ljy_cloudy on 2018/10/6.
 */
public class TimerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("initTimerFilter ");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TimerFilter start");
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        System.out.println("耗时： " + (end - start));
        System.out.println("TimerFilter end");
    }

    @Override
    public void destroy() {

    }
}
