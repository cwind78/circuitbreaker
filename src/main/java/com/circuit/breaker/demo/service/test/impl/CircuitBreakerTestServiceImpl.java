package com.circuit.breaker.demo.service.test.impl;

import com.circuit.breaker.demo.service.test.CircuitBreakerTestService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CircuitBreakerTestServiceImpl {
    private final String BACKEND = "backendA";
//    @Override
    @CircuitBreaker(name = BACKEND, fallbackMethod = "fastFailedReturn")
//    @RateLimiter(name = BACKEND)
//    @Bulkhead(name = BACKEND)
//    @Retry(name = BACKEND, fallbackMethod = "fallback")
//    @TimeLimiter(name = BACKEND)
    public String getDefaultExceptionReturn(long plug) {
        log.info("param plug value: {}", plug);
        if (plug > 5) {
            runtimeException();
            return "bye world!";
        } else {
            return "hello world!";
        }
    }

    public void runtimeException() {
        throw new RuntimeException("failed");
    }

    public String fastFailedReturn(long plug, RuntimeException e) {
        log.info("fast failed");
        return "fast failed";
    }
}
