package com.Brasilprev.gateways.h2;

import java.util.List;
import java.util.Optional;

import com.Brasilprev.gateways.h2.domains.Product;
import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;

public interface ProductGateway {
	
	Product save(ProductRequest productRequest);

	Optional<Product> getProduct(Integer idProduct);

	Optional<List<Product>> getProducts();
}
