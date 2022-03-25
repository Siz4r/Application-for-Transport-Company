package com.example.licencjat.orders;

import com.example.licencjat.UI.Annotations.PreAuthorizeAdmin;
import com.example.licencjat.UI.Annotations.PreAuthorizeAdminAndClient;
import com.example.licencjat.UI.Annotations.PreAuthorizeAdminAndEmployee;
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

    @PostMapping("{stuffId}/{userId}")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    @PreAuthorizeAdminAndClient
    public void addOrder(@Valid @RequestBody OrderWebInput webInput,
                         @PathVariable("stuffId") UUID stuffId,
                         @PathVariable("userId") UUID userId) {
        orderService.addOrder(OrderCommand.builder()
                .stuffId(stuffId)
                .userId(userId)
                .webInput(webInput).build());
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorizeAdmin
    public List<OrderListDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorizeAdmin
    public OrderDetailsDto getOrderById(@PathVariable("id") UUID id) {
        return orderService.getOrderById(OrderCommand.builder().orderId(id).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    @PreAuthorizeAdmin
    public void deleteOrder(@PathVariable("id") UUID id) {
        orderService.deleteOrder(OrderCommand.builder().orderId(id).build());
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED, reason = "Employee assigned succesfully")
    @PreAuthorizeAdminAndEmployee
    public void changeOrderState(@PathVariable("id") UUID id) {
        orderService.changeOrderState(OrderCommand.builder().orderId(id).build());
    }

    @PatchMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource updated successfully")
    @PreAuthorizeAdminAndClient
    public void updateOrderQuantity(@PathVariable("id") UUID id, @Valid @RequestBody OrderWebInput webInput) {
        orderService.updateOrderQuantity(OrderCommand.builder().orderId(id).webInput(webInput).build());
    }
}
