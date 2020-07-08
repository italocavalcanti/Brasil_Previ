package com.Brasilprev.gateways.http.assembler;

import java.util.ArrayList;
import java.util.List;

import com.Brasilprev.domains.ProductDomain;
import com.Brasilprev.gateways.http.jsons.responses.ProductResponse;

public class ProductHttpAssembler {

	public static ProductResponse parseObject(ProductDomain productDomain) {
		return ProductResponse.builder()
				  .idProduct(productDomain.getIdProduct())
				  .nameProduct(productDomain.getNameProduct())
				  .descriptionProduct(productDomain.getDescriptionProduct())
				  .value(productDomain.getValue())
				  .quantity(productDomain.getQuantity())
				.build();
	}

	public static List<ProductResponse> parseObjectList(List<ProductDomain> productsDomain) {

		List<ProductResponse> productsResponse = new ArrayList<>();

		productsDomain.stream().map(ProductHttpAssembler::parseObject).forEach(productsResponse::add);

		return productsResponse;
	}

}