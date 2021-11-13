package com.example.licencjat.stuff.models;

import com.example.licencjat.company.models.Company;
import com.example.licencjat.orders.models.Order;
import com.example.licencjat.user.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Stuff {
    @Id
    private String Id;
    private Integer quantity;
    private String name;
    private int prize;
    private String description;

    @JsonBackReference
    @ManyToOne
    private Company company;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "stuff",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setStuff(this);
    }
}
