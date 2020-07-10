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
import com.Brasilprev.gateways.http.assembler.OrderHttpAssembler;
import com.Brasilprev.gateways.http.jsons.requests.OrderRequest;
import com.Brasilprev.gateways.http.jsons.responses.OrderResponse;
import com.Brasilprev.gateways.http.log.JsonLogger;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.OrderOrchestrator;
import com.Brasilprev.usecases.exceptions.ValidationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.undertow.util.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
@Api(value = "/api/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController extends CustomRestExceptionHandler {

	private final JsonLogger jsonLogger;
	private final OrderOrchestrator orderOrchestrator;

	@Autowired
	public OrderController(JsonLogger jsonLogger, OrderOrchestrator orderOrchestrator) {
		this.jsonLogger = jsonLogger;
		this.orderOrchestrator = orderOrchestrator;
	}

	@ApiOperation(value = "Created order Brasil Prev")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "order Created"),
			@ApiResponse(code = 400, message = "Bad request creating order"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating order"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<?> saveOrder(@RequestBody OrderRequest orderRequest) {

		ResponseEntity<?> response = null;
		OrderResponse orderResponse;
		log.info("LogKey {}, Received Order post request {}", LogKey.ORDER_REQUEST.toString(),
				jsonLogger.toJson(orderRequest));

		try {
			orderResponse = OrderHttpAssembler.parseObject(orderOrchestrator.saveOrder(orderRequest));
			response = new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
		} catch (BadRequestException e) {
			response = new ResponseEntity<>("Integration error: ", HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (ValidationException e) {
			response = new ResponseEntity<>("Validation Data: " + e.getErrorsMap(), HttpStatus.BAD_REQUEST);
		} 

		return response;	
	}

	@ApiOperation(value = "Search Order by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success to find the Order"),
			@ApiResponse(code = 404, message = "Order not found"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating order"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("{id}")
	public ResponseEntity<?> getOrderByID(@PathVariable("id") Integer id) throws BadRequestException {

		log.info("LogKey {}, Received id order request {}", LogKey.ORDER_BY_ID_REQUEST.toString(),
				jsonLogger.toJson(id));
		ResponseEntity<?> response = null;

		try {
			OrderResponse orderResponse = OrderHttpAssembler.parseObject(orderOrchestrator.getOrder(id));
			response = new ResponseEntity<>(orderResponse, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			log.error("Not found error. {} ", e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@ApiOperation(value = "Search Orders ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success to find the Orders"),
			@ApiResponse(code = 404, message = "Orders not found"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating order"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping
	public ResponseEntity<?> getOrders() throws BadRequestException {

		log.info("LogKey {}, Received request for all orders {}", LogKey.ALL_ORDERS_REQUEST.toString());
		ResponseEntity<?> response = null;

		try {
			List<OrderResponse> OrdersResponse = OrderHttpAssembler.parseObjectList(orderOrchestrator.getOrders());
			response = new ResponseEntity<>(OrdersResponse, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			log.error("Not found error. {} ", e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}
}