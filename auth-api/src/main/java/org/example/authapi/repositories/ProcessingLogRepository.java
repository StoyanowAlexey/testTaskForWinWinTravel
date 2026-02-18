package org.example.authapi.repositories;

import org.example.authapi.entities.ProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProcessingLogRepository extends JpaRepository<ProcessingLog, UUID> {
}
