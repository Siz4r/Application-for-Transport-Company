package com.example.licencjat.orders.models;

import com.example.licencjat.client.models.Client;
import com.example.licencjat.employee.models.Employee;
import com.example.licencjat.stuff.models.Stuff;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Stuff_order")
public class Order {
    @Id
    private String id;

    @JsonBackReference
    @ManyToOne
    private Client client;

    @JsonBackReference
    @ManyToOne
    private Employee employee;

    @JsonBackReference
    @ManyToOne
    private Stuff stuff;

    private int amount;
}
