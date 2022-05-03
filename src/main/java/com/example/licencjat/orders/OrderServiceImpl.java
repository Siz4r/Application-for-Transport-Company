package com.example.licencjat.orders;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.client.ClientRepository;
import com.example.licencjat.client.models.ClientOrderDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeOrderDto;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.IncorrectInputDataException;
import com.example.licencjat.exceptions.IllegalArgumentExceptions.NotEnoughResourceAmount;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.orders.models.Order;
import com.example.licencjat.orders.models.OrderCommand;
import com.example.licencjat.orders.models.OrderDetailsDto;
import com.example.licencjat.orders.models.OrderListDto;
import com.example.licencjat.security.AuthenticationFacade;
import com.example.licencjat.stuff.StuffRepository;
import com.example.licencjat.stuff.models.Stuff;
import com.example.licencjat.stuff.models.StuffOrderDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final StuffRepository stuffRepository;
    private final ClientRepository clientRepository;
    private final AuthenticationFacade authenticationFacade;
    private final OrderRepository orderRepository;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;

    @Override
    public void addOrder(OrderCommand command) {
        var stuff = stuffRepository.findById(command.getStuffId()).orElseThrow(IncorrectIdInputException::new);

        checkIfThereIsEnoughAmount(command, stuff);

        var clientList = clientRepository.findAll()
                .stream().filter(c -> c.getUser().getId().equals(command.getUserId()))
                .collect(Collectors.toList());

        if (clientList.size() == 0) {
            throw new IncorrectIdInputException();
        }

        var client = clientList.get(0);

        var order = mapper.map(command.getWebInput(), Order.class);
        order.setId(UUID.fromString(idGenerator.generateId()));
        order.setDone(false);

        stuff.setQuantity(stuff.getQuantity() - command.getWebInput().getAmount());
        stuff.addOrder(order);
        stuffRepository.save(stuff);

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
        var userRole = authenticationFacade.getCurrentAuthenticatedUserAuthorities();
        var user = authenticationFacade.getCurrentAuthenticatedUser();

        if (userRole.get(0).equals("C00")) {
            return orders.stream()
                    .filter(o -> o.getClient().getUser().getId()==user.getId())
                    .map(o -> mapper.map(o, OrderListDto.class))
                    .collect(Collectors.toList());
        }

        return orders.stream()
                .map(o -> mapper.map(o, OrderListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailsDto getOrderById(OrderCommand command) {
        var order = orderRepository.findById(command.getOrderId()).orElseThrow(IncorrectIdInputException::new);
        var employee = new EmployeeOrderDto();
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
    public void changeOrderState(OrderCommand command) {
        var order = orderRepository.findById(command.getOrderId()).orElseThrow(IncorrectIdInputException::new);

        order.setDone(!order.isDone());

        orderRepository.save(order);
    }

    @Override
    public void updateOrderQuantity(OrderCommand command) {
        var order = orderRepository.findById(command.getOrderId()).orElseThrow(IncorrectIdInputException::new);

        var differnceInAmount = order.getAmount() - command.getWebInput().getAmount();
        var stuff = order.getStuff();
        if (stuff.getQuantity() + differnceInAmount < 0) {
            throw new IncorrectInputDataException("There is no enough resources!");
        }
        stuff.setQuantity(stuff.getQuantity() + differnceInAmount);

        order.setAmount(command.getWebInput().getAmount());
        stuffRepository.save(stuff);
        orderRepository.save(order);
    }

    private void checkIfThereIsEnoughAmount(OrderCommand command, Stuff stuff) {
        if (stuff.getQuantity() < command.getWebInput().getAmount()) {
            throw new NotEnoughResourceAmount("There isn't enough " + stuff.getName() + ".");
        }
    }
}
