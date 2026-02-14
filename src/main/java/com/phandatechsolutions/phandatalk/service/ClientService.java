package com.phandatechsolutions.phandatalk.service;

import com.phandatechsolutions.phandatalk.model.ClientEntity;
import com.phandatechsolutions.phandatalk.model.CreditTransactionEntity;
import com.phandatechsolutions.phandatalk.repository.ClientRepository;
import com.phandatechsolutions.phandatalk.repository.CreditTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final CreditTransactionRepository creditTransactionRepository;

    public ClientService(ClientRepository clientRepository,
                         CreditTransactionRepository creditTransactionRepository) {
        this.clientRepository = clientRepository;
        this.creditTransactionRepository = creditTransactionRepository;
    }

    public ClientEntity getClientByApiKey(String apiKey) {
        return clientRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new RuntimeException("API key inválida"));
    }

    public int getClientBalance(String apiKey) {
        ClientEntity client = getClientByApiKey(apiKey);
        return client.getCredits();
    }

    @Transactional
    public CreditTransactionEntity rechargeClient(String apiKey, int creditsToAdd, String externalReference) {
        ClientEntity client = getClientByApiKey(apiKey);

        client.setCredits(client.getCredits() + creditsToAdd);
        clientRepository.save(client);

        CreditTransactionEntity tx = new CreditTransactionEntity();
        tx.setClient(client);
        tx.setCreditsChanged(creditsToAdd);
        tx.setReason("RECHARGE");
        tx.setExternalReference(externalReference);
        tx.setCreatedBy(client.getId());
        tx.setActivatedBy(client.getId());
        tx.setCreatedAt(LocalDateTime.now());
        tx.setActivatedAt(LocalDateTime.now());

        return creditTransactionRepository.save(tx);
    }
}
