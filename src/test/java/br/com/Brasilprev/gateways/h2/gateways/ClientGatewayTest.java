package br.com.Brasilprev.gateways.h2.gateways;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Brasilprev.domains.ClientDomain;
import com.Brasilprev.gateways.h2.ClientGateway;
import com.Brasilprev.gateways.h2.ClientGatewayImpl;
import com.Brasilprev.gateways.h2.ClientRepository;
import com.Brasilprev.gateways.h2.ProductRepository;
import com.Brasilprev.gateways.h2.domains.Client;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;

import io.undertow.util.BadRequestException;

@ExtendWith(MockitoExtension.class)
public class ClientGatewayTest {

	@InjectMocks
	private ClientGatewayImpl clientGateway;

	@Mock
	ClientRepository clientRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnAllClients() {

		// GIVEN list Clients
		when(clientRepository.findAll()).thenReturn(getClients());

		//WHEN client gateway is call 
		Optional<List<Client>> clients = clientGateway.getClients();

		//THEN compare cpf
		assertEquals("0970604401", clients.get().get(0).getCpf());
	}
	
	@Test
	public void saveClientCorrectly() throws BadRequestException {
		// GIVEN a client valid must return id 1

		ClientRequest clientRequest = ClientRequest.builder()
											 .cpf("1234566")
											 .name("ITALO REIS")
											 .numberContact("0912356")
											.build();

		// WHEN the saveClient is call
		Mockito.when(clientRepository.save(Mockito.any())).thenReturn(getClients().get(0));
		Client clientSave = clientGateway.save(clientRequest);

		// THEN return client id 1
		assertEquals("0970123312", clientSave.getNumberContact());
	}

	private List<Client> getClients() {
		Client buildClient = Client.builder()
									.cpf("0970604401")
									.idClient(1)
									.nameClient("italo")
									.numberContact("0970123312")
									.build();

		return Arrays.asList(buildClient);
	}
}
