package com.phandatechsolutions.phandatalk.repository;

import com.phandatechsolutions.phandatalk.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByApiKey(String apiKey);
}
