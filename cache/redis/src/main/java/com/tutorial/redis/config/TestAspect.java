package com.tutorial.redis.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    @Pointcut("@annotation(cacheable)")
    protected void cacheableMethod(Cacheable cacheable) {
    }

    @Around(value = "cacheableMethod(cacheable)")
    public Object logExecutionMethod(ProceedingJoinPoint joinPoint, Cacheable cacheable)
            throws Throwable {
        return joinPoint.proceed();
    }
}
