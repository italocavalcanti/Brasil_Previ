package com.Brasilprev.gateways.h2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Brasilprev.gateways.h2.domains.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
