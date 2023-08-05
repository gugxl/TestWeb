package com.example.demo.incerteptor;

import com.example.demo.annotation.MyOperation;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod)handler;
            System.out.println("用户想执行的操作是:"+h.getMethodAnnotation(MyOperation.class).value());
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
