package com.popov.research.web;

import com.popov.research.domain.CombinedResult;
import com.popov.research.service.CompletableFutureResearch;
import com.popov.research.service.ExecutorsResearch;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value="/api/async")
public class AsyncController {

    private final CompletableFutureResearch completableFutureResearch;

    private final ExecutorsResearch executorsResearch;

    @GetMapping("/start1")
    public String getAsyncResultOption1() throws Exception {
        log.debug("Accepted async request...");
        Future<String> stringFuture = completableFutureResearch.calculateAsync("Hello", "World");
        return stringFuture.get();
    }

    @GetMapping("/start2")
    public String getAsyncResultOption2() throws Exception {
        log.debug("Accepted async request...");
        Future<String> stringFuture = completableFutureResearch.calculateAsync2("Hello", "World");
        return stringFuture.get();
    }

    @GetMapping("/start3")
    public CombinedResult getAsyncResultOption3() throws Exception {
        log.debug("Accepted async request...");
        Future<CombinedResult> stringFuture = completableFutureResearch.calculateAsyncNew("Hello", "World");
        return stringFuture.get();
    }

    @GetMapping("/start4")
    public CombinedResult getAsyncResultOption4() throws Exception {
        log.debug("Accepted async request...");
        Future<CombinedResult> stringFuture = completableFutureResearch.calculateAsync2New("Hello", "World");
        return stringFuture.get();
    }

    // For same type results
    @GetMapping("/start-executors")
    public String getAsyncExecutorsResult() throws Exception {
        log.debug("Accepted async request for Executors...");
        return executorsResearch.calculateAsync("Hello", "World");
    }
}
