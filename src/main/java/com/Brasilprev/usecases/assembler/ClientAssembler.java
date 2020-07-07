package com.Brasilprev.usecases.assembler;

import java.util.ArrayList;
import java.util.List;

import com.Brasilprev.domains.ClientDomain;
import com.Brasilprev.gateways.h2.domains.Client;

public class ClientAssembler {

	public static ClientDomain toDomain(Client clientRequest) {
		return ClientDomain.builder()
							.cpf(clientRequest.getCpf())
							.nameClient(clientRequest.getNameClient())
							.idClient(clientRequest.getIdClient())
							.numberContact(clientRequest.getNumberContact())
				           .build();
	}

	public static List<ClientDomain> listToDomain(List<Client> clientRequest) {
		List<ClientDomain> clientsDomain = new ArrayList<>();
		clientRequest.stream().map(ClientAssembler::toDomain).forEach(clientsDomain::add);

		return clientsDomain;
	}
}
