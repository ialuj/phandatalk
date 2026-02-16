package com.phandatechsolutions.phandatalk.config;

import io.netty.channel.ChannelOption;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Value("${africastalking.apiKey}")
    private String apiKey;

    @Bean
    public WebClient africaWebClient() {
        HttpClient httpClient = HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE) // força DNS padrão
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // timeout de conexão
                .responseTimeout(Duration.ofSeconds(10)); // timeout de resposta

        return WebClient.builder()
                .baseUrl("https://api.africastalking.com/version1")
                .defaultHeader("apiKey", apiKey)
                .defaultHeader("Content-Type", "application/x-www-form-urlencoded")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
