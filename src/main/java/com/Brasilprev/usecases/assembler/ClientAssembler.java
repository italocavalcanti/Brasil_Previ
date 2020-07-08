package com.Brasilprev.usecases.assembler;

import java.util.ArrayList;
import java.util.List;

import com.Brasilprev.domains.ClientDomain;
import com.Brasilprev.gateways.h2.domains.Client;

public class ClientAssembler {

	public static ClientDomain toDomain(Client client) {
		return ClientDomain.builder()
							.cpf(client.getCpf())
							.nameClient(client.getNameClient())
							.idClient(client.getIdClient())
							.numberContact(client.getNumberContact())
				           .build();
	}

	public static List<ClientDomain> listToDomain(List<Client> clients) {
		List<ClientDomain> clientsDomain = new ArrayList<>();
		clients.stream().map(ClientAssembler::toDomain).forEach(clientsDomain::add);

		return clientsDomain;
	}
}
