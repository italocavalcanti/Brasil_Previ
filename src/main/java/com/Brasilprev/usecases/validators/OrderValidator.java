package com.Brasilprev.usecases.validators;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.Brasilprev.gateways.http.jsons.requests.OrderRequest;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.exceptions.ValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderValidator {

	public static final String CLIENT = "Client";
	public static final String PRODUCTS = "Products";
	public static final String TOTAL_VALUE = "Total Value";
	public static final String DESCRIPTION = "Invalid data";

	// logic for validations
	public static void validatorOrderRequest(OrderRequest orderRequest) throws ValidationException {
		log.info(" Order Validation: {} ", LogKey.ORDER_VALIDATION);
		Map<String, String> erros = new HashMap<String, String>();

		if (StringUtils.isEmpty(orderRequest.getClientRequest())) {
			ClientValidator.validatorClientRequest(orderRequest.getClientRequest());
			erros.put(CLIENT, DESCRIPTION);
		}

		if (StringUtils.isEmpty(orderRequest.getProductsRequest())) {
			ProductValidator.validatorProductRequest(orderRequest.getProductsRequest());
			erros.put(PRODUCTS, DESCRIPTION);
		}
		
		if (StringUtils.isEmpty(orderRequest.getTotalValue()) &&
				orderRequest.getTotalValue().compareTo(BigDecimal.valueOf(0)) < 1) {
			erros.put(TOTAL_VALUE, DESCRIPTION);
		}
		

		if (!erros.isEmpty()) {
			throw new ValidationException(erros);
		}
	}

}
