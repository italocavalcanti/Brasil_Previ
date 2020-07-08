package com.Brasilprev.gateways.h2.assemblers;

import com.Brasilprev.gateways.h2.domains.Client;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;

public class ClientAssembler {

	public static Client parseObject(ClientRequest clientRequest) {
		return Client
				.builder()
				.cpf(clientRequest.getCpf())
				.nameClient(clientRequest.getName())
				.numberContact(clientRequest.getNumberContact())
				.build();
	}
}