package com.phandatechsolutions.phandatalk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestDTO {

    @NotBlank(message = "API key é obrigatória")
    private String apiKey;

    @NotBlank(message = "Telefone é obrigatório")
    private String phone;

    @Size(max = 255)
    private String subject; // opcional

    @NotBlank(message = "Conteúdo é obrigatório")
    @Size(max = 1000)
    private String content;

    @NotBlank(message = "Canal é obrigatório")
    private String channel; // SMS ou WHATSAPP

    private String provider; // opcional, default AFRICASTALKING
}
