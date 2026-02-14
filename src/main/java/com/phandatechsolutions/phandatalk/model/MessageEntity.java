package com.phandatechsolutions.phandatalk.model;

import com.phandatechsolutions.phandatalk.enums.MessageChannel;
import com.phandatechsolutions.phandatalk.enums.MessageStatus;
import com.phandatechsolutions.phandatalk.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class MessageEntity extends BaseEntity {

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content", nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "channel", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageChannel channel;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "provider_message_id", nullable = false)
    private String providerMessageId;

    public MessageEntity(String phone, String subject, String content, MessageChannel channel, MessageStatus status, ClientEntity client, String provider, String providerMessageId) {
        this.phone = phone;
        this.subject = subject;
        this.content = content;
        this.channel = channel;
        this.status = status;
        this.client = client;
        this.provider = provider;
        this.providerMessageId = providerMessageId;
    }
}
