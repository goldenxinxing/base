package com.tutorial.webmvc.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncController {
    @Async
    @GetMapping("async_result")
    public CompletableFuture<String> getResultAsync(HttpServletRequest request) throws InterruptedException {
        Thread.sleep(500);
        return CompletableFuture.completedFuture("result is ready!");
    }
}
