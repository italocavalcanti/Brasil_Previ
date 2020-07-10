package com.Brasilprev.gateways.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Brasilprev.domains.exceptions.ResourceNotFoundException;
import com.Brasilprev.gateways.http.assembler.ClientHttpAssembler;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;
import com.Brasilprev.gateways.http.jsons.responses.ClientResponse;
import com.Brasilprev.gateways.http.log.JsonLogger;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.ClientOrchestrator;
import com.Brasilprev.usecases.exceptions.ValidationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.undertow.util.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/client")
@Api(value = "/api/v1/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

	private final JsonLogger jsonLogger;
	private final ClientOrchestrator clientOrchestrator;

	@Autowired
	public ClientController(JsonLogger jsonLogger, ClientOrchestrator clientOrchestrator) {
		this.jsonLogger = jsonLogger;
		this.clientOrchestrator = clientOrchestrator;
	}

	@ApiOperation(value = "Created client Brasil Prev")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "client Created"),
			@ApiResponse(code = 400, message = "Bad request creating client"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating client"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<?> saveClient(@RequestBody ClientRequest clientRequest) {

		ResponseEntity<?> response = null;
		ClientResponse clientResponse;
		log.info("LogKey {}, Received Client post request {}", LogKey.CLIENT_REQUEST.toString(),
				jsonLogger.toJson(clientRequest));

		try {
			clientResponse = ClientHttpAssembler.parseObject(clientOrchestrator.saveClient(clientRequest));
			response = new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
		} catch (BadRequestException e) {
			response = new ResponseEntity<>("Integration error ", HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (ValidationException e) {
			response = new ResponseEntity<>("Validation Data " + e.getErrorsMap(), HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@ApiOperation(value = "Search Client by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success to find the Client"),
			@ApiResponse(code = 404, message = "Client not found"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating client"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("{id}")
	public ResponseEntity<?> getClientByID(@PathVariable("id") Integer id) throws BadRequestException {

		log.info("LogKey {}, Received id client request {}", LogKey.CLIENT_BY_ID_REQUEST.toString(),
				jsonLogger.toJson(id));
		ResponseEntity<?> response = null;

		try {
			ClientResponse clientResponse = ClientHttpAssembler.parseObject(clientOrchestrator.execute(id));
			response = new ResponseEntity<>(clientResponse, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			log.error("Not found error. {} ", e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@ApiOperation(value = "Search Clients ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success to find the Clients"),
			@ApiResponse(code = 404, message = "Clients not found"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating client"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping
	public ResponseEntity<?> getClients() throws BadRequestException {

		log.info("LogKey {}, Received request for all clients {}", LogKey.ALL_CLIENTS_REQUEST.toString());
		ResponseEntity<?> response = null;

		try {
			List<ClientResponse> ClientsResponse = ClientHttpAssembler.parseObjectList(clientOrchestrator.getClients());
			response = new ResponseEntity<>(ClientsResponse, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			log.error("Not found error. {} ", e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}
}