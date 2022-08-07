package com.example.finalProject.repository;

import com.example.finalProject.model.ClientRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRoleRepository extends JpaRepository<ClientRole, Long> {

    ClientRole findByName(String name);
}
