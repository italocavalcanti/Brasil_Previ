package com.Brasilprev.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Brasilprev.domains.OrderDomain;
import com.Brasilprev.domains.exceptions.ResourceNotFoundException;
import com.Brasilprev.gateways.h2.OrderGateway;
import com.Brasilprev.gateways.h2.domains.Order;
import com.Brasilprev.gateways.http.jsons.requests.OrderRequest;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.assembler.OrderAssembler;
import com.Brasilprev.usecases.exceptions.ValidationException;
import com.Brasilprev.usecases.validators.OrderValidator;

import io.undertow.util.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderOrchestrator {
	private final OrderGateway orderGateway;

	@Autowired
	public OrderOrchestrator(OrderGateway orderGateway) {
		this.orderGateway = orderGateway;
	}

	public OrderDomain saveOrder(OrderRequest orderRequest) throws BadRequestException, ValidationException {

		OrderValidator.validatorOrderRequest(orderRequest);

		log.info("Saving Order Request: {} ", LogKey.ORDER_REQUEST);

		return OrderAssembler.toDomain(orderGateway.save(orderRequest));
	}

	public OrderDomain getOrder(Integer idOrder) throws ResourceNotFoundException, BadRequestException {

		Optional<Order> order = orderGateway.getOrder(idOrder);

		if (!order.isPresent()) {
			throw new ResourceNotFoundException(String.format("Order not found. ID - %s ", idOrder));
		}
		return OrderAssembler.toDomain(order.get());
	}

	public List<OrderDomain> getOrders() throws ResourceNotFoundException, BadRequestException {

		log.info("Return Orders");

		Optional<List<Order>> orders = orderGateway.getOrders();

		if (!orders.isPresent()) {
			throw new ResourceNotFoundException(String.format("Orders not found. "));
		}
		return OrderAssembler.listToDomain(orders.get());
	}
}