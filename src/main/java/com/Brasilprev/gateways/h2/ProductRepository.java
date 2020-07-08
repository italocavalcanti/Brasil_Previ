package com.Brasilprev.gateways.h2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Brasilprev.gateways.h2.domains.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
