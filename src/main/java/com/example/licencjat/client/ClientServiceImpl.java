package com.example.licencjat.client;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.user.authorities.models.ROLES;
import com.example.licencjat.client.models.Client;
import com.example.licencjat.client.models.ClientCommand;
import com.example.licencjat.client.models.ClientDto;
import com.example.licencjat.client.models.ClientListDto;
import com.example.licencjat.exceptions.ForbiddenException;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.security.AuthenticationFacade;
import com.example.licencjat.user.UserServiceImpl;
import com.example.licencjat.user.models.UserServiceCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    private final ModelMapper mapper;
    private final IdGenerator idGenerator;
    private final AuthenticationFacade authenticationFacade;
    private final UserServiceImpl userService;

    @Override
    public UUID addClient(ClientCommand command) {
        var user = userService.addUser(UserServiceCommand.builder().webInput(command.getWebInput()).build(), ROLES.CLIENT);

        var id = UUID.fromString(idGenerator.generateId());

        clientRepository.save(Client.builder().id(id).user(user).build());

        return id;
    }

    @Override
    public void addAdmin(ClientCommand command) {
        var user = userService.addUser(UserServiceCommand.builder().webInput(command.getWebInput()).build(), ROLES.ADMIN);

        clientRepository.save(Client.builder().id(UUID.fromString(idGenerator.generateId())).user(user).build());
    }


    @Override
    public void deleteClient(ClientCommand command) {
        var client = clientRepository.findById(command.getId()).orElseThrow(IncorrectIdInputException::new);

        for (var o :
                client.getOrderList()) {
            o.setClient(null);
        }

        clientRepository.deleteById(command.getId());
    }

    @Override
    public ClientDto getClientById(ClientCommand command) {
        var client = clientRepository.findById(command.getId()).orElseThrow(() -> new IncorrectIdInputException(""));

        var id = authenticationFacade.getCurrentAuthenticatedUser().getId();

        if (authenticationFacade.isCurrentAuthenticatedUserAnAdmin()) {
            return mapper.map(client, ClientDto.class);
        }

        if (!id.equals(client.getUser().getId())) {
            throw new ForbiddenException();
        }

        return mapper.map(client, ClientDto.class);
    }

    @Override
    public List<ClientListDto> getClients() {
        var clients = clientRepository.findAll();

        var id = authenticationFacade.getCurrentAuthenticatedUser().getId();

        if (authenticationFacade.isCurrentAuthenticatedUserAnAdmin()) {
            return clients.stream()
                    .map(c -> mapper.map(c, ClientListDto.class))
                    .collect(Collectors.toList());
        }

        return clients.stream()
                .filter(c -> c.getUser().getId().equals(id))
                .map(c -> mapper.map(c, ClientListDto.class))
                .collect(Collectors.toList());
    }
}
