package com.Brasilprev.domains;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderDomain {
	
	private Integer idOrder;
	private ClientDomain clientDomain;
	private Set<ProductDomain> products;
	private BigDecimal totalValue;
}