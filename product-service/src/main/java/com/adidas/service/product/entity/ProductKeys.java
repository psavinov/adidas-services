package com.adidas.service.product.entity;

import java.util.Arrays;

/**
 * @author Pavel Savinov
 */
public enum ProductKeys {

	id, name, model_number, product_type, meta_data, view_list,
	pricing_information, attribute_list, callouts, product_description,
	product_link_list, breadcrumb_list, reviews_number, average_score,
	error_message;

	public ProductKeys getProductKey(String name) {
		return Arrays.asList(ProductKeys.values()).stream().filter(
			value -> value.name().equals(name)
		).findFirst().orElse(null);
	}

}
