package com.example.licencjat.client.models;

import com.example.licencjat.orders.models.Order;
import com.example.licencjat.userData.models.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Client {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "client"
    )
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setClient(this);
    }
}
