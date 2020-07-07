package com.Brasilprev.usecases.assembler;

import java.util.ArrayList;
import java.util.List;

import com.Brasilprev.domains.ProductDomain;
import com.Brasilprev.gateways.h2.domains.Product;

public class ProductAssembler {

	public static ProductDomain toDomain(Product product) {
		return ProductDomain.builder()
							.idProduct(product.getIdProduct())
							.nameProduct(product.getNameProduct())
							.descriptionProduct(product.getDescriptionProduct())
				           .build();
	}

	public static List<ProductDomain> listToDomain(List<Product> productRequest) {
		List<ProductDomain> productsDomain = new ArrayList<>();
		productRequest.stream().map(ProductAssembler::toDomain).forEach(productsDomain::add);

		return productsDomain;
	}
}
