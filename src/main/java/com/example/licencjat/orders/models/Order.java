package com.example.licencjat.orders.models;

import com.example.licencjat.client.models.Client;
import com.example.licencjat.employee.employeeCRUD.models.Employee;
import com.example.licencjat.stuff.models.Stuff;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "stuff_id", nullable = false)
    private Stuff stuff;

    private int amount;
    private Timestamp date = new Timestamp(System.currentTimeMillis());
    private boolean isDone;

    public void addClient(Client client) {
        setClient(client);
        client.addOrder(this);
    }

    public void addStuff(Stuff stuff) {
        setStuff(stuff);
        stuff.addOrder(this);
    }
}
