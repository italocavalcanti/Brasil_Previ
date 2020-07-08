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
							.quantity(product.getQuantity())
							.value(product.getValue())
				           .build();
	}

	public static List<ProductDomain> listToDomain(List<Product> products) {
		List<ProductDomain> productsDomain = new ArrayList<>();
		products.stream().map(ProductAssembler::toDomain).forEach(productsDomain::add);

		return productsDomain;
	}
}