package com.sample.springasyncsamplewebflux.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AsyncService {

    public Mono<String> async() {
        log.info("async call");

        /*try {
            // wait 2s
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //return Mono.just("async call").delayElement(Duration.ofSeconds(10));
        return Mono.just("async call");
    }
}
