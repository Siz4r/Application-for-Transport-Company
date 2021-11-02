package com.example.licencjat.user;

import com.example.licencjat.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
