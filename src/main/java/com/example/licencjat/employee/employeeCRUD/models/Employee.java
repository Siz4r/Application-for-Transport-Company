package com.example.licencjat.employee.employeeCRUD.models;

import com.example.licencjat.orders.models.Order;
import com.example.licencjat.userData.models.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "employee"
    )
    private Set<Order> orderList = new HashSet<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setEmployee(this);
    }

    public void deleteOrder(Order order) {
        orderList.remove(order);
        order.setEmployee(null);
    }
}
