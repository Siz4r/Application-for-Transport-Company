package com.example.licencjat.orders;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.client.ClientRepository;
import com.example.licencjat.client.models.ClientOrderDto;
import com.example.licencjat.employee.employeeCRUD.models.EmployeeOrderDto;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.orders.models.Order;
import com.example.licencjat.orders.models.OrderCommand;
import com.example.licencjat.orders.models.OrderDetailsDto;
import com.example.licencjat.orders.models.OrderListDto;
import com.example.licencjat.security.AuthenticationFacade;
import com.example.licencjat.stuff.StuffRepository;
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
    private final AuthenticationFacade authenticationFacade;
    private final OrderRepository orderRepository;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;
    private final OrderValidator orderValidator = new OrderValidator();

    @Override
    public void addOrder(OrderCommand command) {
        var stuff = stuffRepository.findById(command.getStuffId()).orElseThrow(IncorrectIdInputException::new);

        orderValidator.checkIfThereIsEnoughAmountWhileCreatingOrderAndUpdateStuff(stuff, command.getWebInput().getAmount());

        var client = clientRepository.findClientByUserId(authenticationFacade.getCurrentAuthenticatedUser().getId()).orElseThrow(IncorrectIdInputException::new);
        var order = mapper.map(command.getWebInput(), Order.class);

        order.addStuff(stuff);
        order.setId(UUID.fromString(idGenerator.generateId()));
        order.setDone(false);
        order.addClient(client);

        stuffRepository.save(stuff);
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

        var orderDto = mapper.map(order, OrderDetailsDto.class);

        if (orderDto.getEmployee() == null) orderDto.setEmployee(new EmployeeOrderDto());
        if (orderDto.getClient() == null) orderDto.setClient(getDefaultClient());

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

        var stuff = orderValidator.checkIfThereIsEnoughAmountAfterUpdateAndUpdateStuff(order, command.getWebInput().getAmount());

        order.setAmount(command.getWebInput().getAmount());

        if (command.getWebInput().getAmount() == 0) {
            order.setDone(true);
        }

        stuffRepository.save(stuff);
        orderRepository.save(order);
    }
}
