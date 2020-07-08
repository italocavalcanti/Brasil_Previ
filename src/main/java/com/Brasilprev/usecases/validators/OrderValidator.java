package com.Brasilprev.usecases.validators;

import com.Brasilprev.gateways.http.jsons.requests.OrderRequest;
import com.Brasilprev.gateways.http.log.LogKey;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class OrderValidator {

	public static void validatorOrderRequest(OrderRequest orderRequest) {
		log.info(" Order Validation: {} ", LogKey.ORDER_VALIDATION);
	}

}
