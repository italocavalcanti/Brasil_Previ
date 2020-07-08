package com.Brasilprev.gateways.h2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Brasilprev.gateways.h2.domains.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
