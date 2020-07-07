package com.Brasilprev.gateways.http.assembler;

import java.util.ArrayList;
import java.util.List;

import com.Brasilprev.domains.ClientDomain;
import com.Brasilprev.gateways.http.jsons.responses.ClientResponse;

public class ClientHttpAssembler {

	public static ClientResponse parseObject(ClientDomain clientDomain) {
		return ClientResponse.builder()
				.idClient(clientDomain.getIdClient())
				.cpf(clientDomain.getCpf())
				.name(clientDomain.getNameClient())
				.numberContact(clientDomain.getNumberContact())
				.build();
	}

	public static List<ClientResponse> parseObjectList(List<ClientDomain> clientsDomain) {

		List<ClientResponse> clientsResponse = new ArrayList<>();

		clientsDomain.stream().map(ClientHttpAssembler::parseObject).forEach(clientsResponse::add);

		return clientsResponse;
	}

}