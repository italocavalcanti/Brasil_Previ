package com.Brasilprev.gateways.http.jsons.responses;

import java.math.BigDecimal;
import java.util.Set;

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
public class OrderResponse {

	@ApiModelProperty(value = "ID order process")
	private Integer idOrder;

	@ApiModelProperty(value = "Client", required = true)
	private ClientResponse clientResponse;

	@ApiModelProperty(value = "Products")
	private Set<ProductResponse> productResponse;

	@ApiModelProperty(value = "Total Value")
	private BigDecimal totalValue;

}