package com.example.licencjat.orders;

import com.example.licencjat.orders.models.OrderCommand;
import com.example.licencjat.orders.models.OrderDetailsDto;
import com.example.licencjat.orders.models.OrderListDto;
import com.example.licencjat.orders.models.OrderWebInput;
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
    public OrderDetailsDto getOrderById(@PathVariable("id") UUID id) {
        return orderService.getOrderById(OrderCommand.builder().orderId(id).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    public void deleteOrder(@PathVariable("id") UUID id) {
        orderService.deleteOrder(OrderCommand.builder().orderId(id).build());
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED, reason = "Employee assigned succesfully")
    public void markOrderAsDone(@PathVariable("id") UUID id) {
        orderService.markOrderAsDoen(OrderCommand.builder().orderId(id).build());
    }

    @PatchMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource updated successfully")
    public void updateOrderQuantity(@PathVariable("id") UUID id, @RequestBody OrderWebInput webInput) {
        orderService.updateOrderQuantity(OrderCommand.builder().orderId(id).webInput(webInput).build());
    }
}
