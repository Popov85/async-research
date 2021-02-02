package com.popov.research.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Slf4j
class CompletableFutureResearchTest {

    @Test
    void calculateSync() {
        CompletableFutureResearch cfr = new CompletableFutureResearch(new RestTemplate());
        log.debug("Before submitting...");
        Future<String> stringFuture = cfr.calculateAsync("Hello", "World!");
        try {
            String result = stringFuture.get();
            log.debug("Result = {}", result);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        log.debug("After submitting...");
    }
}