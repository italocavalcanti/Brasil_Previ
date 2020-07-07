package com.Brasilprev.gateways.http.jsons.requests;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@NotNull
	@Size(max = 100)
	@NotEmpty
	@ApiModelProperty(value = "Name Product", required = true, example = "ITALO")
	private String nameProduct;

	@NotNull
	@ApiModelProperty(value = "Description Product", required = true)
	private String descriptionProduct;

}