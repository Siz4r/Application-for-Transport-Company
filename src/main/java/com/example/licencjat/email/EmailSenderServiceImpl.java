package com.example.licencjat.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Override
    public void sendEmail(String email) {
        System.out.println("problem z czyms tam" + this);
        log.warn("problem z czyms tam{},{},{}", this, this, this);
    }

    @Override
    public String toString() {
        return "dupa1234";
    }
}
