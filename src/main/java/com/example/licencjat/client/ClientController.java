package com.example.licencjat.client;

import com.example.licencjat.client.models.ClientDto;
import com.example.licencjat.client.models.ClientListDto;
import com.example.licencjat.client.models.ClientCommand;
import com.example.licencjat.user.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/clients/")
public class ClientController {
    private final ClientService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ClientListDto> getClients() {
        return service.getClients();
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ClientDto getClientById(@PathVariable("id") UUID id) {
        return service.getClientById(ClientCommand.builder().id(id).build());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    public void createAClient(@RequestBody UserWebInput webInput) {
        service.addClient(ClientCommand.builder().webInput(webInput).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    public void deleteAClient(@PathVariable("id") UUID id) {
        service.deleteClient(ClientCommand.builder().id(id).build());
    }
}
