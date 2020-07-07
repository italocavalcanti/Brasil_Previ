package com.Brasilprev.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Brasilprev.domains.ClientDomain;
import com.Brasilprev.domains.exceptions.ResourceNotFoundException;
import com.Brasilprev.gateways.h2.ClientGateway;
import com.Brasilprev.gateways.h2.domains.Client;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.assembler.ClientAssembler;
import com.Brasilprev.usecases.validators.ClientValidator;

import io.undertow.util.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientOrchestrator {
	private final ClientGateway clientGateway;

	@Autowired
	public ClientOrchestrator(ClientGateway clientGateway) {
		this.clientGateway = clientGateway;
	}

	public ClientDomain saveClient(ClientRequest clientRequest) throws BadRequestException {

		ClientValidator.validatorClientRequest(clientRequest);

		log.info("Saving Client Request: {} ", LogKey.CLIENT_REQUEST);

		return ClientAssembler.toDomain(clientGateway.save(clientRequest));
	}

	public ClientDomain execute(Integer idClient) throws ResourceNotFoundException, BadRequestException {

		Optional<Client> client = clientGateway.getClient(idClient);

		if (!client.isPresent()) {
			throw new ResourceNotFoundException(String.format("Client not found. ID - %s ", idClient));
		}
		return ClientAssembler.toDomain(client.get());
	}

	public List<ClientDomain> getClients() throws ResourceNotFoundException, BadRequestException {

		log.info("Return Clients");

		Optional<List<Client>> clients = clientGateway.getClients();

		if (!clients.isPresent()) {
			throw new ResourceNotFoundException(String.format("Clients not found. "));
		}
		return ClientAssembler.listToDomain(clients.get());
	}
}