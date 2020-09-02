package com.myboot.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Nansen
 * @create 2020/6/5 15:15
 */
@Configuration
@WebFilter(urlPatterns="/*")
public class JobFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("In JobFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
