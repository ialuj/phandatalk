package com.phandatechsolutions.phandatalk.model;

import com.phandatechsolutions.phandatalk.enums.ClientStatus;
import com.phandatechsolutions.phandatalk.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class ClientEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "message_limit", nullable = false)
    private int messageLimit;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(nullable = false)
    private Integer credits;

    @Column(nullable = false)
    private String paymentReference;

    public ClientEntity(String name, String apiKey, String email, String phone, int messageLimit, ClientStatus status) {
        this.name = name;
        this.apiKey = apiKey;
        this.email = email;
        this.phone = phone;
        this.messageLimit = messageLimit;
        this.status = status;
    }
}
