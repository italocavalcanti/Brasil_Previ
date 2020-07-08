package com.Brasilprev.usecases.validators;

import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;
import com.Brasilprev.gateways.http.log.LogKey;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ProductValidator {

	public static void validatorProductRequest(ProductRequest productRequest) {
		log.info(" Product Validation: {} ", LogKey.CLIENT_VALIDATION);
	}

}
