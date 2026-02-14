package com.phandatechsolutions.phandatalk.repository;

import com.phandatechsolutions.phandatalk.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
