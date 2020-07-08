package com.Brasilprev.gateways.http.jsons.responses;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProductResponse {

	@ApiModelProperty(value = "Id Product")
	private Integer idProduct;

	@ApiModelProperty(value = "Name Product")
	private String nameProduct;

	@ApiModelProperty(value = "Description Product")
	private String descriptionProduct;
	
	@ApiModelProperty(value = "Quantity")
	private Integer quantity;

	@ApiModelProperty(value = "Value")
	private BigDecimal value;
}