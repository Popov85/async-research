package com.popov.research.service;

import com.popov.research.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ExecutorsResearch {

    private static final String BASE_URL="http://localhost:8085/api/users";

    private static final String DEFAULT = BASE_URL+"/default";
    private static final String SPECIAL = BASE_URL+"/special";
    private static final String EXTREME = BASE_URL+"/extreme";

    private final RestTemplate restTemplate;


    /**
     * A lot of result of equal type(!), say 10-100
     */
    public String calculateAsync(String first, String second) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<User>> futureResults = executorService.invokeAll(Arrays.asList(
                () -> restTemplate.getForObject(DEFAULT, User.class),
                () -> restTemplate.getForObject(SPECIAL, User.class),
                () -> restTemplate.getForObject(EXTREME, User.class)
                ));


        String result = futureResults.stream().map(u -> {
            try {
                return u.get().getName();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining(":"));
        return first.concat(" ").concat(second).concat(" ").concat(result).concat("!");
    }
}
