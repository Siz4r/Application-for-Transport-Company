package com.example.licencjat.orders;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.client.ClientRepository;
import com.example.licencjat.client.models.ClientOrderDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeOrderDto;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.NotEnoughResourceAmount;
import com.example.licencjat.orders.models.Order;
import com.example.licencjat.orders.models.OrderCommand;
import com.example.licencjat.orders.models.OrderDetailsDto;
import com.example.licencjat.orders.models.OrderListDto;
import com.example.licencjat.stuff.StuffRepository;
import com.example.licencjat.stuff.models.Stuff;
import com.example.licencjat.stuff.models.StuffOrderDto;
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
        var stuff = stuffRepository.findById(command.getStuffId()).orElseThrow(IncorrectIdInputException::new);
        checkIfThereIsEnoughAmount(command, stuff);
        var client = clientRepository.findById(command.getClientId()).orElseThrow(IncorrectIdInputException::new);

        //Create
        var order = mapper.map(command.getWebInput(), Order.class);
        order.setId(UUID.fromString(idGenerator.generateId()));
        order.setDone(false);

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
    public OrderDetailsDto getOrderById(OrderCommand command) {
        var order = orderRepository.findById(command.getOrderId()).orElseThrow(IncorrectIdInputException::new);
        EmployeeOrderDto employee = new EmployeeOrderDto();
        if (order.getEmployee() != null) {
            employee = mapper.map(order.getEmployee(), EmployeeOrderDto.class);
        }

        var client = mapper.map(order.getClient(), ClientOrderDto.class);
        var stuff = mapper.map(order.getStuff(), StuffOrderDto.class);

        var orderDto = mapper.map(order, OrderDetailsDto.class);
        orderDto.setClient(client);
        orderDto.setEmployee(employee);
        orderDto.setStuff(stuff);

        return orderDto;
    }

    @Override
    public void markOrderAsDoen(OrderCommand command) {
        var order = orderRepository.findById(command.getOrderId()).orElseThrow(IncorrectIdInputException::new);

        order.setDone(true);

        orderRepository.save(order);
    }

    @Override
    public void updateOrderQuantity(OrderCommand command) {
        var order = orderRepository.findById(command.getOrderId()).orElseThrow(IncorrectIdInputException::new);
        order.setAmount(command.getWebInput().getAmount());
        orderRepository.save(order);
    }

    private void checkIfThereIsEnoughAmount(OrderCommand command, Stuff stuff) {
        if (stuff.getQuantity() < command.getWebInput().getAmount()) {
            throw new NotEnoughResourceAmount("There isn't enough " + stuff.getName() + ".");
        }
    }
}
