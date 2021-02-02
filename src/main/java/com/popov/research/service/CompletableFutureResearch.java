package com.popov.research.service;

import com.popov.research.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompletableFutureResearch {

    private static final String BASE_URL = "http://localhost:8085/api/users";

    private static final String DEFAULT = BASE_URL+"/default";
    private static final String SPECIAL = BASE_URL+"/special";
    private static final String EXTREME = BASE_URL+"/extreme";

    private final RestTemplate restTemplate;

    /**
     * Some of the services returning different types (2-5 services)
     */
    public Future<String> calculateAsync(String first, String second) {

        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> restTemplate.getForObject(DEFAULT, User.class))
                .thenCompose(defaultUser -> CompletableFuture.supplyAsync(() -> defaultUser.getName() +
                        ":" + restTemplate.getForObject(SPECIAL, User.class).getName()))
                .thenCompose(defaultAndSpecialUser -> CompletableFuture.supplyAsync(() -> defaultAndSpecialUser +
                        ":" + restTemplate.getForObject(EXTREME, User.class).getName()))
                .thenApply(result->first.concat(" ").concat(second).concat(" ").concat(result).concat("!"));

        log.debug("Returned...");
        return completableFuture;
    }

    /**
     * Some of the services returning different types (5-10 services, better scalability)
     */
    public Future<String> calculateAsync2(String first, String second) {

        CompletableFuture<User> defaultStage = CompletableFuture.supplyAsync(() -> restTemplate.getForObject(DEFAULT, User.class));
        CompletableFuture<User> specialStage = CompletableFuture.supplyAsync(() -> restTemplate.getForObject(SPECIAL, User.class));
        CompletableFuture<User> extremeStage = CompletableFuture.supplyAsync(() -> restTemplate.getForObject(EXTREME, User.class));

        CompletionStage<String> combinedDataCompletionStage = CompletableFuture
                .allOf(defaultStage, specialStage, extremeStage)
                .thenApply(ignoredVoid -> joinAllResults(defaultStage.join(), specialStage.join(), extremeStage.join()))
                .thenApply(result->first.concat(" ").concat(second).concat(" ").concat(result).concat("!"));

        log.debug("Returned...");
        return combinedDataCompletionStage.toCompletableFuture();
    }

    private String joinAllResults(User defaultUser, User specialUser, User extremeUser) {
        return defaultUser.getName().concat(":").concat(specialUser.getName()).concat(":").concat(extremeUser.getName());
    }
}
