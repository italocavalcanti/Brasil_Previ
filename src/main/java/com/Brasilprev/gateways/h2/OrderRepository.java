package com.Brasilprev.gateways.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Brasilprev.gateways.h2.domains.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
