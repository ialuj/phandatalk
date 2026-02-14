package com.phandatechsolutions.phandatalk.controller;

import com.phandatechsolutions.phandatalk.dto.ClientBalanceDTO;
import com.phandatechsolutions.phandatalk.dto.RechargeRequestDTO;
import com.phandatechsolutions.phandatalk.dto.RechargeResponseDTO;
import com.phandatechsolutions.phandatalk.model.CreditTransactionEntity;
import com.phandatechsolutions.phandatalk.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Messages", description = "API para registo de clientes, recargas e consultas de saldos")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Consulta saldo do cliente")
    @GetMapping("/balance")
    public ResponseEntity<ClientBalanceDTO> getBalance(@RequestParam String apiKey) {
        int credits = clientService.getClientBalance(apiKey);

        ClientBalanceDTO dto = new ClientBalanceDTO();
        dto.setApiKey(clientService.getClientByApiKey(apiKey).getName());
        dto.setCredits(credits);

        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Recarrega créditos do cliente")
    @PostMapping("/recharge")
    public ResponseEntity<RechargeResponseDTO> recharge(@Valid @RequestBody RechargeRequestDTO request) {
        CreditTransactionEntity tx = clientService.rechargeClient(
                request.getApiKey(),
                request.getCreditsToAdd(),
                request.getExternalReference()
        );

        RechargeResponseDTO response = new RechargeResponseDTO();
        response.setClientApiKey(tx.getClient().getName());
        response.setCreditsAdded(tx.getCreditsChanged());
        response.setTotalCredits(tx.getClient().getCredits());
        response.setExternalReference(tx.getExternalReference());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
