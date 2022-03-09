package com.example.licencjat.orders.models;

import com.example.licencjat.client.models.Client;
import com.example.licencjat.employee.employeeCRUD.models.Employee;
import com.example.licencjat.stuff.models.Stuff;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Stuff_order")
public class Order {
    @Id
    private UUID id;

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
    private Timestamp date = new Timestamp(System.currentTimeMillis());
    private boolean isDone;
}
