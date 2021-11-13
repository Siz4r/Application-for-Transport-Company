package com.example.licencjat.client;

import com.example.licencjat.client.models.Client;
import com.example.licencjat.client.models.ClientDto;
import com.example.licencjat.client.models.ClientListDto;
import com.example.licencjat.client.models.ClientCommand;
import com.example.licencjat.exceptions.IncorrectIdInputException;
import com.example.licencjat.user.IdGenerator;
import com.example.licencjat.user.UserServiceImpl;
import com.example.licencjat.user.models.UserServiceCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    private final ModelMapper mapper;
    private final IdGenerator idGenerator;
    private final UserServiceImpl userService;

    @Override
    public void addClient(ClientCommand command) {
        var user = userService.addUser(UserServiceCommand.builder().webInput(command.getWebInput()).build());

        var id = idGenerator.generateId();

        clientRepository.save(Client.builder().id(id).user(user).build());
    }

    @Override
    public void deleteClient(ClientCommand command) {
        clientRepository.deleteById(command.getId());
    }

    @Override
    public ClientDto getClientById(ClientCommand command) {
        var client = clientRepository.findById(command.getId()).orElseThrow(() -> new IncorrectIdInputException(""));

        return mapper.map(client, ClientDto.class);
    }

    @Override
    public List<ClientListDto> getClients() {
        var clients = clientRepository.findAll();

        return clients.stream()
                .map(c -> mapper.map(c, ClientListDto.class))
                .collect(Collectors.toList());
    }
}
