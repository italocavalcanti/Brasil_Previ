package com.Brasilprev.gateways.http.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Brasilprev.domains.OrderDomain;
import com.Brasilprev.domains.ProductDomain;
import com.Brasilprev.gateways.http.jsons.responses.ClientResponse;
import com.Brasilprev.gateways.http.jsons.responses.OrderResponse;
import com.Brasilprev.gateways.http.jsons.responses.ProductResponse;

public class OrderHttpAssembler {

	public static OrderResponse parseObject(OrderDomain orderDomain) {
		
		return OrderResponse.builder()
							 .idOrder(orderDomain.getIdOrder())
							 .totalValue(orderDomain.getTotalValue())
				 			 .clientResponse(ClientResponse
				 					 .builder()
				 					  .cpf(orderDomain.getClientDomain().getCpf())
				 					  .idClient(orderDomain.getClientDomain().getIdClient())
				 					  .name(orderDomain.getClientDomain().getNameClient())
				 					  .numberContact(orderDomain.getClientDomain().getNumberContact())
				 					 .build())
				 			 .productResponse(getProductsResponse(orderDomain.getProducts()))
							.build();
	}

	private static Set<ProductResponse> getProductsResponse(Set<ProductDomain> products) {
		Set<ProductResponse> setProduct = new HashSet<>();

		products.forEach(pr->{
			setProduct.add(ProductResponse.builder()
								   .descriptionProduct(pr.getDescriptionProduct())
								   .idProduct(pr.getIdProduct())
								   .nameProduct(pr.getNameProduct())
								   .value(pr.getValue())
								   .quantity(pr.getQuantity())
								  .build());
		});
		
		return setProduct;
	}

	public static List<OrderResponse> parseObjectList(List<OrderDomain> ordersDomain) {

		List<OrderResponse> ordersResponse = new ArrayList<>();

		ordersDomain.stream().map(OrderHttpAssembler::parseObject).forEach(ordersResponse::add);

		return ordersResponse;
	}

}