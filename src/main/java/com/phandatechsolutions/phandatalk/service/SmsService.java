package com.phandatechsolutions.phandatalk.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SmsService {

    private final WebClient webClient;
    private final String username;

    public SmsService(WebClient africaWebClient,
                      @Value("${africastalking.username}") String username) {
        this.webClient = africaWebClient;
        this.username = username;
    }

    public Mono<String> sendSms(String to, String message) {
        String body = "username=" + username +
                "&to=" + to +
                "&message=" + message;

        System.out.println("Body: " + body);
        return webClient.post()
                .uri("/messaging")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }
}

