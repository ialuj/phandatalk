package com.phandatechsolutions.phandatalk.controller;

import com.phandatechsolutions.phandatalk.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sms")
@Tag(name = "SMS", description = "API para envio de SMS")
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @Operation(summary = "Envia uma mensagem")
    @PostMapping("/send-sms")
    public Mono<String> sendSms(
            @RequestParam String to,
            @RequestParam String message
    ) {
        return smsService.sendSms(to, message);
    }
}
