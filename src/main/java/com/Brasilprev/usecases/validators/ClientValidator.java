package com.Brasilprev.usecases.validators;

import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;
import com.Brasilprev.gateways.http.log.LogKey;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ClientValidator {

	public static void validatorClientRequest(ClientRequest clientRequest) {
		log.info(" Client Validation: {} ", LogKey.CLIENT_VALIDATION);
	}

}
