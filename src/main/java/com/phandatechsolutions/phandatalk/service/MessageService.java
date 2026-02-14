package com.phandatechsolutions.phandatalk.service;

import com.phandatechsolutions.phandatalk.enums.ClientStatus;
import com.phandatechsolutions.phandatalk.model.*;
import com.phandatechsolutions.phandatalk.repository.ClientRepository;
import com.phandatechsolutions.phandatalk.repository.CreditTransactionRepository;
import com.phandatechsolutions.phandatalk.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;
    private final CreditTransactionRepository creditTransactionRepository;

    public MessageService(ClientRepository clientRepository,
                          MessageRepository messageRepository,
                          CreditTransactionRepository creditTransactionRepository) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.creditTransactionRepository = creditTransactionRepository;
    }

    @Transactional
    public MessageEntity sendMessage(String apiKey, String phone, String content,
                                     String subject, String channel, String provider) {

        ClientEntity client = clientRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new RuntimeException("API key inválida"));

        // verifica status do cliente
        if(client.getStatus() != ClientStatus.ACTIVE) {
            throw new RuntimeException("Cliente inativo, suspenso ou bloqueado");
        }

        // verifica créditos
        if(client.getCredits() <= 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        // cria mensagem
        MessageEntity message = new MessageEntity();
        message.setPhone(phone);
        message.setContent(content);
        message.setSubject(subject);
        message.setChannel(Enum.valueOf(com.phandatechsolutions.phandatalk.enums.MessageChannel.class, channel));
        message.setStatus(com.phandatechsolutions.phandatalk.enums.MessageStatus.PENDING);
        message.setProvider(provider);
        message.setClient(client);

        // auditoria
        message.setCreatedBy(client.getId());
        message.setActivatedBy(client.getId());
        message.setCreatedAt(LocalDateTime.now());
        message.setActivatedAt(LocalDateTime.now());

        // salva mensagem
        messageRepository.save(message);

        // deduz créditos do cliente
        client.setCredits(client.getCredits() - 1);
        clientRepository.save(client);

        // registra histórico de créditos
        CreditTransactionEntity tx = new CreditTransactionEntity();
        tx.setClient(client);
        tx.setCreditsChanged(-1);
        tx.setReason("MESSAGE_SENT");
        tx.setExternalReference(null);
        tx.setCreatedBy(client.getId());
        tx.setActivatedBy(client.getId());
        tx.setCreatedAt(LocalDateTime.now());
        tx.setActivatedAt(LocalDateTime.now());

        creditTransactionRepository.save(tx);

        return message;
    }
}
