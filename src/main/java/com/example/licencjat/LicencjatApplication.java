package com.example.licencjat;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.licencjat.files.CloudinaryApiConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2
public class LicencjatApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicencjatApplication.class, args);


    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }


}

/**
 * To do:
 * Nazwa musi być unikalna, dodać firme sprzedajaca do towaru oraz opis
 * Stworzyć usera, klienta i pracownika
 */