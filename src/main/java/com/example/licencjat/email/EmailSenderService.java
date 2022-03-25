package com.example.licencjat.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendEmail(String toEmail, String subject, String body);
}
