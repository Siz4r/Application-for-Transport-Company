package com.example.licencjat.client;

import com.example.licencjat.client.models.ClientDto;
import com.example.licencjat.client.models.ClientListDto;
import com.example.licencjat.client.models.ClientCommand;

import java.util.List;
import java.util.UUID;

interface ClientService {
    UUID addClient(ClientCommand command);
    void deleteClient(ClientCommand command);
    ClientDto getClientById(ClientCommand command);
    List<ClientListDto> getClients();
}
