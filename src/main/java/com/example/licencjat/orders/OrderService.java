package com.example.licencjat.orders;

import com.example.licencjat.orders.models.OrderCommand;
import com.example.licencjat.orders.models.OrderDto;
import com.example.licencjat.orders.models.OrderListDto;

import java.util.List;

interface OrderService {
    void addOrder(OrderCommand command);
    void deleteOrder(OrderCommand command);
    List<OrderListDto> getOrders();
    OrderDto getOrderById(OrderCommand command);
}
