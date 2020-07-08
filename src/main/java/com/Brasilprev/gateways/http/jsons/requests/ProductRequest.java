package com.Brasilprev.gateways.http.jsons.requests;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class ProductRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 520842543117147690L;
	
	@ApiModelProperty(value = "ID Product", example = "1")
	private Integer id;

	@NotNull
	@Size(max = 100)
	@NotEmpty
	@ApiModelProperty(value = "Name Product", required = true, example = "ITALO")
	private String nameProduct;

	@NotNull
	@ApiModelProperty(value = "Description Product", required = true)
	private String descriptionProduct;
	
	@NotNull
	@NotEmpty
	@ApiModelProperty(value = "Total Value U$", required = true, example = "110.00")
	private BigDecimal value;
	
	@NotNull
	@NotEmpty
	@ApiModelProperty(value = "Quantity", required = true, example = "1")
	private Integer quantity;
	
}