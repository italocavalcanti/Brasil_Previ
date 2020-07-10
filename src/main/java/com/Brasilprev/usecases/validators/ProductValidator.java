package com.Brasilprev.usecases.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;
import com.Brasilprev.gateways.http.log.LogKey;
import com.Brasilprev.usecases.exceptions.ValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductValidator {

	public static final String DESCRIPTIONPRODUCT = "Description Product";
	public static final String NAMEPRODUCT = "Name Product";
	public static final String VALUE = "value Product";
	public static final String QUANTITY = "quantity Product";
	public static final String DESCRIPTION = "Invalid data";

	// logic for validations RN
	public static void validatorProductRequest(ProductRequest productRequest)
			throws ValidationException {

		Map<String, String> erros = new HashMap<String, String>();

		log.info(" Product Validation: {} ", LogKey.PRODUCT_VALIDATION);

		if (StringUtils.isEmpty(productRequest.getDescriptionProduct())) {
			erros.put(DESCRIPTIONPRODUCT, DESCRIPTION);
		}

		if (StringUtils.isEmpty(productRequest.getNameProduct())) {
			erros.put(NAMEPRODUCT, DESCRIPTION);
		}

		if (StringUtils.isEmpty(productRequest.getValue())) {
			erros.put(VALUE, DESCRIPTION);
		}

		if (StringUtils.isEmpty(productRequest.getQuantity())) {
			erros.put(QUANTITY, DESCRIPTION);
		}

		if (!erros.isEmpty()) {
			throw new ValidationException(erros);
		}
	}

	public static void validatorProductRequest(Set<ProductRequest> productsRequest) throws ValidationException  {
		for (ProductRequest productRequest : productsRequest) {
			ProductValidator.validatorProductRequest(productRequest);
		}
	}
}