package com.Brasilprev.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Brasilprev.domains.ProductDomain;
import com.Brasilprev.domains.exceptions.ResourceNotFoundException;
import com.Brasilprev.gateways.h2.ProductGateway;
import com.Brasilprev.gateways.h2.domains.Product;
import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.assembler.ProductAssembler;
import com.Brasilprev.usecases.validators.ProductValidator;

import io.undertow.util.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductOrchestrator {
	private final ProductGateway productGateway;

	@Autowired
	public ProductOrchestrator(ProductGateway productGateway) {
		this.productGateway = productGateway;
	}

	public ProductDomain saveProduct(ProductRequest productRequest) throws BadRequestException {

		ProductValidator.validatorProductRequest(productRequest);

		log.info("Saving Product Request: {} ", LogKey.PRODUCT_REQUEST);

		return ProductAssembler.toDomain(productGateway.save(productRequest));
	}

	public ProductDomain getProduct(Integer idProduct) throws ResourceNotFoundException, BadRequestException {

		Optional<Product> product = productGateway.getProduct(idProduct);

		if (!product.isPresent()) {
			throw new ResourceNotFoundException(String.format("Product not found. ID - %s ", idProduct));
		}
		return ProductAssembler.toDomain(product.get());
	}

	public List<ProductDomain> getProducts() throws ResourceNotFoundException, BadRequestException {

		log.info("Return Products");

		Optional<List<Product>> products = productGateway.getProducts();

		if (!products.isPresent()) {
			throw new ResourceNotFoundException(String.format("Products not found. "));
		}
		return ProductAssembler.listToDomain(products.get());
	}
}