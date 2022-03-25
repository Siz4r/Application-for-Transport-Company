package com.example.licencjat.client;

import com.example.licencjat.client.models.ClientDto;
import com.example.licencjat.client.models.ClientListDto;
import com.example.licencjat.client.models.ClientCommand;
import com.example.licencjat.stuff.models.StuffServiceCommand;
import com.example.licencjat.userData.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('A00', 'C00')")
    public List<ClientListDto> getClients() {
        return service.getClients();
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('A00', 'C00')")
    public ClientDto getClientById(@PathVariable("id") UUID id) {
        return service.getClientById(ClientCommand.builder().id(id).build());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('A00')")
    public ResponseEntity<String> createAClient(@RequestBody UserWebInput webInput) {
        return new ResponseEntity<>(
                service.addClient(ClientCommand.builder().webInput(webInput).build()).toString(),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    @PreAuthorize("hasAnyAuthority('A00')")
    public void deleteAClient(@PathVariable("id") UUID id) {
        service.deleteClient(ClientCommand.builder().id(id).build());
    }
}
