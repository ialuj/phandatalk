package com.phandatechsolutions.phandatalk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechargeResponseDTO {
    private String clientApiKey;
    private Integer creditsAdded;
    private Integer totalCredits;
    private String externalReference;
}
