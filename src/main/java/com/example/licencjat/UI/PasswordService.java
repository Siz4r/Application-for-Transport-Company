package com.example.licencjat.UI;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordService {
    public String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var rnd = new Random();

        var sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
