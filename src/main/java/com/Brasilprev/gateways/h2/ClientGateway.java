package com.Brasilprev.gateways.h2;

import java.util.List;
import java.util.Optional;

import com.Brasilprev.gateways.h2.domains.Client;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;

public interface ClientGateway {
	
	Client save(ClientRequest clientRequest);

	Optional<Client> getClient(Integer idClient);

	Optional<List<Client>> getClients();
}
