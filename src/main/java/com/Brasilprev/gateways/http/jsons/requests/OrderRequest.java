package com.Brasilprev.gateways.http.jsons.requests;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel
@Getter
@Setter
@ToString
@Builder
public class OrderRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 520842543117147690L;

	@NotNull
	@Size(max = 100)
	@NotEmpty
	@ApiModelProperty(value = "Client", required = true)
	private ClientRequest ClientRequest;

	@NotNull
	@Size(max = 100)
	@NotEmpty
	@ApiModelProperty(value = "Products", required = true)
	private Set<ProductRequest> productsRequest;
	
	@NotNull
	@NotEmpty
	@ApiModelProperty(value = "Total Value", required = true, example = "100.00")
	private BigDecimal totalValue;

}