package com.phandatechsolutions.phandatalk.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechargeRequestDTO {

    @NotBlank(message = "API key é obrigatória")
    private String apiKey;

    @NotNull(message = "Quantidade de créditos é obrigatória")
    @Min(value = 1, message = "Créditos devem ser pelo menos 1")
    private Integer creditsToAdd;

    private String externalReference; // ID da API de pagamentos ou nota
}
