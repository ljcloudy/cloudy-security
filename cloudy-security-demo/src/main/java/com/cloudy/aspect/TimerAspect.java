package com.cloudy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by ljy_cloudy on 2018/10/6.
 */
@Aspect
@Component
public class TimerAspect {

    @Around("execution(* com.cloudy.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long endTime = System.currentTimeMillis();

        System.out.println("timerAspect 耗时： " + (endTime - startTime));
        System.out.println("time aspect end");
        return proceed;
    }
}
