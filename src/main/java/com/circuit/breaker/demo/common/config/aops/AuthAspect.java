package com.circuit.breaker.demo.common.config.aops;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AuthAspect {
    @Around("@annotation(com.circuit.breaker.demo.common.config.annotations.MthdLogger)")
    public Object authcheck(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("catch");
        return joinPoint.proceed();
    }
}