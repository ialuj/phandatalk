package com.phandatechsolutions.phandatalk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDTO {

    private String uuid;
    private String phone;
    private String subject;
    private String content;
    private String channel;
    private String status;
    private String provider;
    private String providerMessageId;
}
