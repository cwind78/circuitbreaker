package com.circuit.breaker.demo.ctrl.test;

import com.circuit.breaker.demo.common.config.annotations.MthdLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultipleDispatcherCtrl {
    @GetMapping("/first/ctrl")
    public String getFirstMethod() {
        return "first";
    }

    @MthdLogger
    @GetMapping("/second/ctrl")
    public String getSecondMethod() {
        return "second";
    }

    @GetMapping("/third/ctrl")
    public String getThirdMethod() {
        return "third";
    }
}
