package com.example.licencjat.orders;

import com.example.licencjat.orders.models.OrderCommand;
import com.example.licencjat.orders.models.OrderDto;
import com.example.licencjat.orders.models.OrderListDto;
import com.example.licencjat.orders.models.OrderWebInput;
import com.example.licencjat.stuff.models.StuffDto;
import com.example.licencjat.stuff.models.StuffListDto;
import com.example.licencjat.stuff.models.StuffServiceCommand;
import com.example.licencjat.stuff.models.StuffUpdateCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders/")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("{stuffId}/{clientId}")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    public void addOrder(@Valid @RequestBody OrderWebInput webInput,
                         @PathVariable("stuffId") UUID stuffId,
                         @PathVariable("clientId") UUID clientId) {
        orderService.addOrder(OrderCommand.builder()
                .stuffId(stuffId)
                .clientId(clientId)
                .webInput(webInput).build());
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<OrderListDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable("id") UUID id) {
        return orderService.getOrderById(OrderCommand.builder().orderId(id).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    public void deleteOrder(@PathVariable("id") UUID id) {
        orderService.deleteOrder(OrderCommand.builder().orderId(id).build());
    }
}
