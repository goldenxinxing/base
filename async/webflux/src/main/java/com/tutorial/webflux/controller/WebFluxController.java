package com.tutorial.webflux.controller;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RestController
public class WebFluxController {
    @GetMapping("/flux_result")
    public Mono<String> getResult() {
        return Mono.defer(() -> Mono.just("result is ready!! flux"))
                .delaySubscription(Duration.ofMillis(500));
    }
}
