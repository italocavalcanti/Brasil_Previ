package com.Brasilprev.gateways.h2;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Brasilprev.gateways.h2.assemblers.ClientAssembler;
import com.Brasilprev.gateways.h2.domains.Client;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;

@Component
public class ClientGatewayImpl implements ClientGateway {

	private ClientRepository clientRepository;

	@Autowired
	public ClientGatewayImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Client save(ClientRequest client) {
		return clientRepository.save(ClientAssembler.parseObject(client));
	}

	@Override
	public Optional<Client> getClient(Integer idClient) {
		return clientRepository.findById(idClient);
	}

	@Override
	public Optional<List<Client>> getClients() {
		return Optional.of(Optional.ofNullable(clientRepository.findAll()).orElseGet(Collections::emptyList));
	}
}