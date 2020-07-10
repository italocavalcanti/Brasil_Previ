package br.com.Brasilprev.gateways.http;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Brasilprev.domains.ClientDomain;
import com.Brasilprev.gateways.http.ClientController;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;
import com.Brasilprev.gateways.http.log.JsonLogger;
import com.Brasilprev.usecases.ClientOrchestrator;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	JsonLogger jsonLogger;
	
	@Mock
	private ClientOrchestrator clientOrchestrator;

	@InjectMocks
	private ClientController clientController;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
	}

	@Test
	public void shouldReturnAllClients() throws Exception {

		// GIVEN list Clients
		when(clientOrchestrator.getClients()).thenReturn(getClients());

		// WHEN the endpoint /api/v1/client/ get is call
		MvcResult result = mockMvc.perform(get("/api/v1/client/")
								   .contentType(MediaType.APPLICATION_JSON))
								  .andDo(MockMvcResultHandlers.print())
								  .andExpect(status().isOk()).andReturn();

		// THEN clientDomain is valid return
		verify(clientOrchestrator).getClients();
	}

	private List<ClientDomain> getClients() {

		return Collections.singletonList(ClientDomain.builder()
													  .cpf("09706044400")
													  .idClient(1)
				                                      .nameClient("italo cavalcanti")
				                                      .numberContact("123445")
				                                     .build());
	}

	@Test
	public void shouldReturnOkWhenPostIsCall() throws Exception {
		
		// GIVEN Client valid
		ClientRequest clientRequest = ClientRequest.builder()
													.name("italo")
													.numberContact("1235567")
													.cpf("09706044400")
												.build();
		String clientResquestJson = new Gson().toJson(clientRequest);
		
		// WHEN the endpoint /api/v1/client/ pos is call
		when(jsonLogger.toJson(Mockito.any())).thenReturn(clientResquestJson);
		when(clientOrchestrator.saveClient(Mockito.any())).thenReturn(getClients().get(0));
		
		// THEN clientDomain is valid return status ok
		mockMvc.perform(post("/api/v1/client").contentType(MediaType.APPLICATION_JSON).content(clientResquestJson))
				.andExpect(status().isCreated());
	}
}