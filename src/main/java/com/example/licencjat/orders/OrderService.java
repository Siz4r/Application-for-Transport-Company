package com.example.licencjat.orders;

import com.example.licencjat.orders.models.OrderCommand;
import com.example.licencjat.orders.models.OrderDetailsDto;
import com.example.licencjat.orders.models.OrderListDto;

import java.util.List;

interface OrderService {
    void addOrder(OrderCommand command);
    List<OrderListDto> getOrders();
    OrderDetailsDto getOrderById(OrderCommand command);
    void changeOrderState(OrderCommand command);
    void updateOrderQuantity(OrderCommand command);
}
