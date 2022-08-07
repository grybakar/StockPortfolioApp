package com.example.finalProject.service;

import com.example.finalProject.model.Client;
import com.example.finalProject.model.ClientRole;

import java.util.List;

public interface ClientService {

    Client saveClient(Client client);

    ClientRole saveRole(ClientRole clientRole);

    void addRoleToUser(String username, String roleName);

    Client getClient(String username);

    List<Client> getClients();
}
