package com.Brasilprev.gateways.h2;

import java.util.List;
import java.util.Optional;

import com.Brasilprev.gateways.h2.domains.Order;
import com.Brasilprev.gateways.http.jsons.requests.OrderRequest;

public interface OrderGateway {
	
	Order save(OrderRequest orderRequest);

	Optional<Order> getOrder(Integer idOrder);

	Optional<List<Order>> getOrders();
}
