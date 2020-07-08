package com.Brasilprev.gateways.h2;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Brasilprev.gateways.h2.assemblers.OrderAssembler;
import com.Brasilprev.gateways.h2.domains.Order;
import com.Brasilprev.gateways.http.jsons.requests.OrderRequest;

@Component
public class OrderGatewayImpl implements OrderGateway {

	private OrderRepository orderRepository;

	@Autowired
	public OrderGatewayImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order save(OrderRequest order) {
		return orderRepository.save(OrderAssembler.parseObject(order));
	}

	@Override
	public Optional<Order> getOrder(Integer idOrder) {
		return orderRepository.findById(idOrder);
	}

	@Override
	public Optional<List<Order>> getOrders() {
		return Optional.of(Optional.ofNullable(orderRepository.findAll()).orElseGet(Collections::emptyList));
	}
}