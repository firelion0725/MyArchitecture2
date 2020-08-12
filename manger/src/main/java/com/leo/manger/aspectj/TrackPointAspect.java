package com.leo.manger.aspectj;

import com.leo.manger.aspectj.annotation.RecordClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author Leo.ZhangTJ
 * @time 2020/5/6
 * @function
 * @describe
 */
@Aspect
public class TrackPointAspect {

    @Pointcut("execution(@com.leo.manger.aspectj.annotation.Record * *(..))")
    public void trackPoint() {
    }

    @Pointcut("execution(@com.leo.manger.aspectj.annotation.RecordClick * *(..))")
    public void trackPointClick() {
    }

    @Around("trackPoint()")
    public void aroundTrackPoint(final ProceedingJoinPoint joinPoint) {
        System.out.println(joinPoint.getTarget().toString() + " Start");
        String name = joinPoint.getSignature().getName();
        long start = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long cost = System.currentTimeMillis() - start;
        System.out.println(name + ":" + cost);
    }

    @Around("trackPointClick()")
    public void aroundTrackPointClick(final ProceedingJoinPoint joinPoint) {
        System.out.println(joinPoint.getTarget().toString() + " click Start");

        String value = "";
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RecordClick annotation = method.getAnnotation(RecordClick.class);
        if (annotation != null) {
            value = annotation.value();
            System.out.println(value);
        }
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println(joinPoint.getTarget().toString() + " throwable");
        }
        System.out.println(joinPoint.getTarget().toString() + " click end");
    }

}
