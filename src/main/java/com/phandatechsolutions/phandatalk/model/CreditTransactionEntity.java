package com.phandatechsolutions.phandatalk.model;

import com.phandatechsolutions.phandatalk.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credit_transactions")
@Getter
@Setter
@NoArgsConstructor
public class CreditTransactionEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @Column(name = "credits_changed", nullable = false)
    private int creditsChanged; // positivo ou negativo

    @Column(name = "reason", nullable = false)
    private String reason; // "MESSAGE_SENT", "RECHARGE", "BONUS"

    @Column(name = "external_reference")
    private String externalReference; // ID da API de pagamentos ou nota
}
