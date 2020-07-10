package com.Brasilprev.usecases.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Brasilprev.domains.ClientDomain;
import com.Brasilprev.domains.OrderDomain;
import com.Brasilprev.domains.ProductDomain;
import com.Brasilprev.gateways.h2.domains.Order;
import com.Brasilprev.gateways.h2.domains.Product;

public class OrderAssembler {

	public static OrderDomain toDomain(Order order) {
		return OrderDomain.builder()
						   .idOrder(order.getIdOrder())
						   .totalValue(order.getTotalValue())
						   .clientDomain(ClientDomain
								.builder()
								 .cpf(order.getClient().getCpf())
								 .idClient(order.getClient().getIdClient())
								 .nameClient(order.getClient().getNameClient())
								 .numberContact(order.getClient().getNumberContact())
								.build())
						    .products(getProducts(order.getProducts()))
						  .build();
	}

	private static Set<ProductDomain> getProducts(Set<Product> products) {
		
		Set<ProductDomain> setProduct = new HashSet<>();

		products.forEach(pr->{
			setProduct.add(ProductDomain.builder()
								   .descriptionProduct(pr.getDescriptionProduct())
								   .idProduct(pr.getIdProduct())
								   .nameProduct(pr.getNameProduct())
								   .value(pr.getValue())
								   .quantity(pr.getQuantity())
								  .build());
		});
		
		return setProduct;
	}

	public static List<OrderDomain> listToDomain(List<Order> orders) {
		List<OrderDomain> ordersDomain = new ArrayList<>();
		orders.stream().map(OrderAssembler::toDomain).forEach(ordersDomain::add);

		return ordersDomain;
	}
}
