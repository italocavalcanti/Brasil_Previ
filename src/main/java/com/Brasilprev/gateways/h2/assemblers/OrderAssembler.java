package com.Brasilprev.gateways.h2.assemblers;

import java.util.HashSet;
import java.util.Set;

import com.Brasilprev.gateways.h2.domains.Client;
import com.Brasilprev.gateways.h2.domains.Order;
import com.Brasilprev.gateways.h2.domains.Product;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;
import com.Brasilprev.gateways.http.jsons.requests.OrderRequest;
import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;

public class OrderAssembler {

	public static Order parseObject(OrderRequest orderRequest) {
		return Order.builder()
						.client(getClient(orderRequest.getClientRequest()))
						.products(getProducts(orderRequest.getProductsRequest()))
						.totalValue(orderRequest.getTotalValue())
					.build();
	}

	private static Set<Product> getProducts(Set<ProductRequest> productsRequest) {
		
		Set<Product> setProduct = new HashSet<Product>();

		productsRequest.forEach(pr->{
			setProduct.add(Product.builder()
								   .descriptionProduct(pr.getDescriptionProduct())
								   .idProduct(pr.getId())
								   .nameProduct(pr.getNameProduct())
								   .value(pr.getValue())
								   .quantity(pr.getQuantity())
								  .build());
		});
		
		return setProduct;
	}

	private static Client getClient(ClientRequest clientRequest) {
		return Client.builder()
						.cpf(clientRequest.getCpf())
						.idClient(clientRequest.getId())
						.nameClient(clientRequest.getName())
						.numberContact(clientRequest.getNumberContact())
					 .build();
	}
}