package com.cht.hadoopDemo.fiter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        //允许跨域
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setHeader("Access-Control-Allow-Origin","*");
        //配置编码
        response.setHeader("Content-Type","text/html;charset=utf-8");
        filterChain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
