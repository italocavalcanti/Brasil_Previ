package com.Brasilprev.gateways.h2;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Brasilprev.gateways.h2.assemblers.ProductAssembler;
import com.Brasilprev.gateways.h2.domains.Product;
import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;

@Component
public class ProductGatewayImpl implements ProductGateway {

	private ProductRepository productRepository;

	@Autowired
	public ProductGatewayImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(ProductRequest product) {
		return productRepository.save(ProductAssembler.parseObject(product));
	}

	@Override
	public Optional<Product> getProduct(Integer idProduct) {
		return productRepository.findById(idProduct);
	}

	@Override
	public Optional<List<Product>> getProducts() {
		return Optional.of(Optional.ofNullable(productRepository.findAll()).orElseGet(Collections::emptyList));
	}
}