package com.example.licencjat.client.models;

import com.example.licencjat.files.models.File;
import com.example.licencjat.orders.models.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDto {
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userPhoneNumber;

    private List<File> userFiles;
    private List<Order> orderList;
}
