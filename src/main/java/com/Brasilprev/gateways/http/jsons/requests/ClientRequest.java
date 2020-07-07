package com.Brasilprev.gateways.http.jsons.requests;

import java.io.Serializable;

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
public class ClientRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 520842543117147690L;

	@NotNull
	@Size(max = 100)
	@NotEmpty
	@ApiModelProperty(value = "Name Client", required = true, example = "ITALO")
	private String name;

	@NotNull
	@ApiModelProperty(value = "CPF", required = true)
	private String cpf;

	@NotNull
	@ApiModelProperty(value = "Number Contact", required = true)
	private String numberContact;

}