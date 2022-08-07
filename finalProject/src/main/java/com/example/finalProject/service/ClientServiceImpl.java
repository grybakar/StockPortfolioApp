package com.example.finalProject.service;

import com.example.finalProject.model.Client;
import com.example.finalProject.model.ClientRole;
import com.example.finalProject.repository.ClientRepository;
import com.example.finalProject.repository.ClientRoleRepository;
import com.example.finalProject.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClientServiceImpl implements ClientService, UserDetailsService {

    private final ClientRepository clientRepository;
    private final ClientRoleRepository clientRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Client saveClient(Client client) {
        log.info("Saving new client {} to the database", client.getUsername());
        client.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(client.getPassword()));
        return clientRepository.save(client);
    }

    @Override
    public ClientRole saveRole(ClientRole clientRole) {
        log.info("Saving new role {} to the database", clientRole.getName());
        return clientRoleRepository.save(clientRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to Client {}", roleName, username);
        Client byUsername = clientRepository.findByUsername(username);
        ClientRole byName = clientRoleRepository.findByName(roleName);
        byUsername.getClientRoles().add(byName);
    }

    @Override
    public Client getClient(String username) {
        log.info("Fetching client {}", username);
        return clientRepository.findByUsername(username);
    }

    @Override
    public List<Client> getClients() {
        log.info("Fetching all clients");
        return clientRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);

        if (client == null) {
            log.error("Client not found in the database");
            throw new UsernameNotFoundException("Client not found in the database");
        } else {
            log.info("Client found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        client.getClientRoles()
                .forEach(clientRole -> authorities
                        .add(new SimpleGrantedAuthority(clientRole.getName())));
        return new org.springframework.security.core.userdetails.User(
                client.getUsername(),
                client.getPassword(),
                authorities);
    }
}
