package org.example.authapi.repositories;

import org.example.authapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User getUserByEmail(String email);

    boolean existsByEmail(String email);
}
