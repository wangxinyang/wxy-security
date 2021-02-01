package com.security.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.security.web.controller.FilterController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("TimeAspect handleControllerMethod");
        System.out.println(pjp.getSignature().getName());
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is : " + arg);
        }
        long start = new Date().getTime();
        Object proceed = pjp.proceed();
        System.out.println("耗时：" + (new Date().getTime() - start));
        System.out.println("TimeAspect handleControllerMethod end");
        return proceed;
    }

}
