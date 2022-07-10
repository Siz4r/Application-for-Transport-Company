package com.example.licencjat.client;

import com.example.licencjat.client.models.Client;
import com.example.licencjat.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findClientByUserId(UUID user_id);
}
