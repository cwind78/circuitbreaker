package com.circuit.breaker.demo.common.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MthdLogger {
    boolean isResultLogging() default false;
    boolean isArgumentLogging() default true;
}
