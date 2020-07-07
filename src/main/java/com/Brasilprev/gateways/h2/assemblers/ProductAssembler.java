package com.Brasilprev.gateways.h2.assemblers;

import com.Brasilprev.gateways.h2.domains.Product;
import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;

public class ProductAssembler {

	public static Product parseObject(ProductRequest productRequest) {
		return Product
				.builder()
				.descriptionProduct(productRequest.getDescriptionProduct())
				.nameProduct(productRequest.getNameProduct())
				.build();
	}
}