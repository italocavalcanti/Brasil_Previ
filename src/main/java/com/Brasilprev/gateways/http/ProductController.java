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
import com.Brasilprev.gateways.http.assembler.ProductHttpAssembler;
import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;
import com.Brasilprev.gateways.http.jsons.responses.ProductResponse;
import com.Brasilprev.gateways.http.log.JsonLogger;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.ProductOrchestrator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.undertow.util.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@Api(value = "/api/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	private final JsonLogger jsonLogger;
	private final ProductOrchestrator productOrchestrator;

	@Autowired
	public ProductController(JsonLogger jsonLogger, ProductOrchestrator productOrchestrator) {
		this.jsonLogger = jsonLogger;
		this.productOrchestrator = productOrchestrator;
	}

	@ApiOperation(value = "Created product Brasil Prev")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Product Created"),
			@ApiResponse(code = 400, message = "Bad request creating product"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating product"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest) {

		ResponseEntity<?> response = null;
		ProductResponse productResponse;
		log.info("LogKey {}, Received Product post request {}", LogKey.PRODUCT_REQUEST.toString(),
				jsonLogger.toJson(productRequest));

		try {
			productResponse = ProductHttpAssembler.parseObject(productOrchestrator.saveProduct(productRequest));
			response = new ResponseEntity<>(productResponse, HttpStatus.CREATED);
		} catch (BadRequestException e) {
			response = new ResponseEntity<>("Integration error", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return response;
	}

	@ApiOperation(value = "Search Product by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success to find the Product"),
			@ApiResponse(code = 404, message = "Product not found"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating product"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("{id}")
	public ResponseEntity<?> getProductByID(@PathVariable("id") Integer id) throws BadRequestException {

		log.info("LogKey {}, Received id product request {}", LogKey.PRODUCT_BY_ID_REQUEST.toString(),
				jsonLogger.toJson(id));
		ResponseEntity<?> response = null;

		try {
			ProductResponse productResponse = ProductHttpAssembler.parseObject(productOrchestrator.getProduct(id));
			response = new ResponseEntity<>(productResponse, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			log.error("Not found error. {} ", e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@ApiOperation(value = "Search Products ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success to find the Products"),
			@ApiResponse(code = 404, message = "Products not found"),
			@ApiResponse(code = 403, message = "Access is forbidden"),
			@ApiResponse(code = 422, message = "Error creating product"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping
	public ResponseEntity<?> getProducts() throws BadRequestException {

		log.info("LogKey {}, Received request for all products {}", LogKey.ALL_PRODUCTS_REQUEST.toString());
		ResponseEntity<?> response = null;

		try {
			List<ProductResponse> ProductsResponse = ProductHttpAssembler.parseObjectList(productOrchestrator.getProducts());
			response = new ResponseEntity<>(ProductsResponse, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			log.error("Not found error. {} ", e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}
}