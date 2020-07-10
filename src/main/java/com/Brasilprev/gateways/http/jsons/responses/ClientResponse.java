package com.Brasilprev.gateways.http.jsons.responses;

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
public class ClientResponse {

	@ApiModelProperty(value = "Id client")
	private Integer idClient;

	@ApiModelProperty(value = "Name Client")
	private String name;

	@ApiModelProperty(value = "CPF")
	private String cpf;

	@ApiModelProperty(value = "Number Contact")
	private String numberContact;

}