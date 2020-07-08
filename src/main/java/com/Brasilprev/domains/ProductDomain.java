package com.Brasilprev.domains;

import java.math.BigDecimal;

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
public class ProductDomain {	
	private Integer idProduct;
	private String nameProduct;
	private String descriptionProduct;
	private Integer quantity;
	private BigDecimal value;
}