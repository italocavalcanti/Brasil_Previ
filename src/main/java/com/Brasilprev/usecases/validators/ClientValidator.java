package com.Brasilprev.usecases.validators;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.exceptions.ValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientValidator {

	public static final String NAME = "Name Client";
	public static final String CPF = "cpf Client";
	public static final String NUMBERCONTACT = "value Product";
	public static final String DESCRIPTION = "Invalid data";

	// logic for validations
	public static void validatorClientRequest(ClientRequest clientRequest) throws ValidationException {
		log.info(" Client Validation: {} ", LogKey.CLIENT_VALIDATION);

		Map<String, String> erros = new HashMap<String, String>();


		if (StringUtils.isEmpty(clientRequest.getName())) {
			erros.put(NAME, DESCRIPTION);
		}

		if (StringUtils.isEmpty(clientRequest.getCpf())) {
			erros.put(CPF, DESCRIPTION);
		}

		if (StringUtils.isEmpty(clientRequest.getNumberContact())) {
			erros.put(NUMBERCONTACT, DESCRIPTION);
		}

		if (!erros.isEmpty()) {
			throw new ValidationException(erros);
		}
	}
}