package com.sample.springasyncsamplewebflux.controller;

import com.sample.springasyncsamplewebflux.service.AsyncService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RequestMapping("webflux")
@RequiredArgsConstructor
@RestController
public class TestController {
    private final AsyncService asyncService;
    WebClient webClient = WebClient.create("http://localhost:8081");

    @GetMapping("call")
    public Mono<String> call() {
        log.info("call");
        return Mono.just("call");
    }

    @GetMapping("nonblock1")
    public Mono<String> api1() {
        return someApi1();
    }

    /*@GetMapping("nonblock2")
    public Mono<String> api2() {
        Flux.zip(someApi1(), someApi1())
                .reduce((s1, s2) -> s1.getT1() + " | " + s2.getT2())


        return someApi1();
    }*/
    @GetMapping("nonblock3")
    public Mono<String> api3() {
        //메인 스레드가 점유
        return asyncService.async();
    }

    @GetMapping("nonblock4")
    public Mono<String> api4() {
        return asyncService.async().subscribeOn(Schedulers.elastic());
    }

    private Mono<String> someApi1() {
        return webClient
                .get()
                .uri("some-api1")
                .retrieve()
                .bodyToMono(String.class);
    }
}
