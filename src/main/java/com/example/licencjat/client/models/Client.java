package com.example.licencjat.client.models;

import com.example.licencjat.orders.models.Order;
import com.example.licencjat.user.models.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Client {
    @Id
    private String id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setClient(this);
    }
}
