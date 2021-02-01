package com.security.interceptor;

import com.sun.xml.internal.ws.client.sei.MethodHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//@Component
public class TimeInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("TimeInterceptor preHandle");
        long start = new Date().getTime();
        request.setAttribute("start", start);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("TimeInterceptor postHandle");
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        Long start = (Long)request.getAttribute("start");
        System.out.println("耗时：" + (new Date().getTime() - start));
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("TimeInterceptor afterCompletion");
        Long start = (Long)request.getAttribute("start");
        System.out.println("耗时：" + (new Date().getTime() - start));
        System.out.println("ex is" + ex);
    }
}
