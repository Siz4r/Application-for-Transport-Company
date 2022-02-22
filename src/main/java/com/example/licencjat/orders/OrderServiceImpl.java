package com.example.licencjat.orders;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.client.ClientRepository;
import com.example.licencjat.exceptions.IncorrectIdInputException;
import com.example.licencjat.exceptions.NotEnoughResourceAmount;
import com.example.licencjat.orders.models.OrderCommand;
import com.example.licencjat.orders.models.OrderDto;
import com.example.licencjat.orders.models.Order;
import com.example.licencjat.orders.models.OrderListDto;
import com.example.licencjat.stuff.StuffRepository;
import com.example.licencjat.stuff.models.Stuff;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final StuffRepository stuffRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;

    @Override
    public void addOrder(OrderCommand command) {
        //Validate&&Get
        var stuff = stuffRepository.findById(command.getStuffId()).orElseThrow(() -> new IncorrectIdInputException(""));
        checkIfThereIsEnoughAmount(command, stuff);
        var client = clientRepository.findById(command.getClientId()).orElseThrow(() -> new IncorrectIdInputException(""));

        //Create
        var order = mapper.map(command.getWebInput(), Order.class);
        order.setId(UUID.fromString(idGenerator.generateId()));

        //SetStuff
        stuff.setQuantity(stuff.getQuantity() - command.getWebInput().getAmount());
        stuff.addOrder(order);
        stuffRepository.save(stuff);

        //SetClient
        client.addOrder(order);
        clientRepository.save(client);

    }

    @Override
    public void deleteOrder(OrderCommand command) {
        orderRepository.deleteById(command.getOrderId());
    }

    @Override
    public List<OrderListDto> getOrders() {
        var orders = orderRepository.findAll();

        return orders.stream()
                .map(o -> mapper.map(o, OrderListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(OrderCommand command) {
        var order = orderRepository.findById(command.getOrderId()).orElseThrow(() -> new IncorrectIdInputException(""));

        return mapper.map(order, OrderDto.class);
    }

    private void checkIfThereIsEnoughAmount(OrderCommand command, Stuff stuff) {
        if (stuff.getQuantity() < command.getWebInput().getAmount()) {
            throw new NotEnoughResourceAmount("");
        }
    }
}
