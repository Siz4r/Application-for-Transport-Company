package com.example.licencjat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LicencjatApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicencjatApplication.class, args);


    }


}

/**
 * To do:
 * Nazwa musi być unikalna, dodać firme sprzedajaca do towaru oraz opis
 * Stworzyć usera, klienta i pracownika
 */