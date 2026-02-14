package com.phandatechsolutions.phandatalk.controller;

import com.phandatechsolutions.phandatalk.dto.MessageRequestDTO;
import com.phandatechsolutions.phandatalk.dto.MessageResponseDTO;
import com.phandatechsolutions.phandatalk.model.MessageEntity;
import com.phandatechsolutions.phandatalk.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@Tag(name = "Messages", description = "API para envio de mensagens SMS e WhatsApp")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(summary = "Envia uma mensagem para um cliente usando API Key")
    @PostMapping("/send")
    public ResponseEntity<MessageResponseDTO> sendMessage(@Valid @RequestBody MessageRequestDTO request) {

        // default provider se não informado
        String provider = request.getProvider() != null ? request.getProvider() : "AFRICASTALKING";

        MessageEntity message = messageService.sendMessage(
                request.getApiKey(),
                request.getPhone(),
                request.getContent(),
                request.getSubject(),
                request.getChannel(),
                provider
        );

        // mapeia para DTO de resposta
        MessageResponseDTO response = new MessageResponseDTO();
        response.setUuid(message.getUuid());
        response.setPhone(message.getPhone());
        response.setSubject(message.getSubject());
        response.setContent(message.getContent());
        response.setChannel(message.getChannel().name());
        response.setStatus(message.getStatus().name());
        response.setProvider(message.getProvider());
        response.setProviderMessageId(message.getProviderMessageId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
