package com.circuit.breaker.demo.ctrl.test;

import com.circuit.breaker.demo.service.test.CircuitBreakerTestService;
import com.circuit.breaker.demo.service.test.impl.CircuitBreakerTestServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CircuitBreakerTestCtrl {
    private final CircuitBreakerTestServiceImpl service;

    @GetMapping("/test/cb")
    public String getCircuitBreakerCallTest(@RequestParam long plug) {
        try {
            return service.getDefaultExceptionReturn(plug);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
