package br.com.Brasilprev.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Brasilprev.domains.ClientDomain;
import com.Brasilprev.domains.exceptions.ResourceNotFoundException;
import com.Brasilprev.gateways.h2.ClientGateway;
import com.Brasilprev.gateways.h2.domains.Client;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;
import com.Brasilprev.usecases.ClientOrchestrator;
import com.Brasilprev.usecases.exceptions.ValidationException;

import io.undertow.util.BadRequestException;

@ExtendWith(MockitoExtension.class)
public class ClientOrchestratorTest {
	
	@InjectMocks
	ClientOrchestrator clientOrchestrator;
	
	@Mock
	private ClientGateway clientGateway;
	

	@Test
	public void saveClientCorrectly() throws BadRequestException, ValidationException {
		// GIVEN a client valid must return id 1

		ClientRequest clientRequest = getClientRequest();

		// WHEN the saveClient is call
		Mockito.when(clientGateway.save(clientRequest)).thenReturn(getClient());

		ClientDomain client = clientOrchestrator.saveClient(clientRequest);

		// THEN return client id 1
		assertEquals(1, client.getIdClient());
	}
	
	@Test
	public void shouldReturnClientByIdSucess() throws BadRequestException, ResourceNotFoundException {
		// GIVEN id Client 1
		Integer idClient = 1;

		// WHEN the getClient is call
		Mockito.when(clientGateway.getClient(idClient)).thenReturn(Optional.of(getClient()));

		ClientDomain clientDomain = clientOrchestrator.execute(idClient);

		// THEN clientDomain is valid return
		assertEquals(1, clientDomain.getIdClient());
		assertEquals("09706044400", clientDomain.getCpf());
		assertEquals("italo reis", clientDomain.getNameClient());
		assertEquals("34536512", clientDomain.getNumberContact());
	}
	
	@Test
	public void shouldReturnClientsSucess() throws BadRequestException, ResourceNotFoundException {

		// WHEN the getClients is call
		Mockito.when(clientGateway.getClients()).thenReturn(Optional.of(Arrays.asList(getClient())));

		List<ClientDomain> clientDomain = clientOrchestrator.getClients();

		// THEN clients Domain is valid return
		assertEquals(1, clientDomain.get(0).getIdClient());
		assertEquals("09706044400", clientDomain.get(0).getCpf());
		assertEquals("italo reis", clientDomain.get(0).getNameClient());
		assertEquals("34536512", clientDomain.get(0).getNumberContact());

	}

	private Client getClient() {
		return Client.builder()
				.idClient(1)
				.cpf("09706044400")
				.nameClient("italo reis")
				.numberContact("34536512")
			.build();
	}

	private ClientRequest getClientRequest() {
		return ClientRequest.builder()
								.cpf("09706044400")
								.name("italo reis")
								.numberContact("34536512")
							.build();
	}
}