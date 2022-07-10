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
import com.example.licencjat.user.UserRepository;
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

        checkIfThereIsEnoughAmount(stuff, command.getWebInput().getAmount());

        var client = clientRepository.findClientByUserId(authenticationFacade.getCurrentAuthenticatedUser().getId()).orElseThrow(IncorrectIdInputException::new);

        var order = mapper.map(command.getWebInput(), Order.class);

        order.setId(UUID.fromString(idGenerator.generateId()));
        order.setDone(false);
        order.setClient(client);

        stuff.setQuantity(stuff.getQuantity() - command.getWebInput().getAmount());
        stuff.addOrder(order);
        stuffRepository.save(stuff);

        client.addOrder(order);
        clientRepository.save(client);
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

        ClientOrderDto client;
        if (order.getClient() != null) {
            client = mapper.map(order.getClient(), ClientOrderDto.class);
        } else {
            client = getDefaultClient();
        }

        var stuff = mapper.map(order.getStuff(), StuffOrderDto.class);

        var orderDto = mapper.map(order, OrderDetailsDto.class);

        orderDto.setClient(client);
        orderDto.setEmployee(employee);
        orderDto.setStuff(stuff);

        return orderDto;
    }

    private ClientOrderDto getDefaultClient() {
        var client = new ClientOrderDto();
        client.setUserFirstName("Użytkownik prawdopodobnie został usunięty!");
        client.setUserBuildingNumber(1);
        client.setUserLastName("");
        client.setUserCity("  ");
        client.setUserPostalCode("  ");
        client.setUserStreet("  ");
        return client;
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

        checkIfThereIsEnoughAmount(stuff, -differnceInAmount);

        stuff.setQuantity(stuff.getQuantity() + differnceInAmount);
        order.setAmount(command.getWebInput().getAmount());

        if (command.getWebInput().getAmount() == 0) {
            order.setDone(true);
        }

        stuffRepository.save(stuff);
        orderRepository.save(order);
    }

    private void checkIfThereIsEnoughAmount(Stuff stuff, int orderedAmount) {
        if (stuff.getQuantity() < orderedAmount) {
            throw new NotEnoughResourceAmount("There isn't enough " + stuff.getName() + ".");
        }
    }
}
