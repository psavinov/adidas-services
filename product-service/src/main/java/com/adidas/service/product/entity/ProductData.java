package com.adidas.service.product.entity;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of {@link Data} which represents a product data based on the
 * Adidas ECom API response.
 *
 * @author Pavel Savinov
 */
public class ProductData
	extends ConcurrentHashMap<String, Object> implements Data  {

	public ProductData() {}

	public ProductData(String productId) {
		super();

		put(ProductKeys.id.name(), productId);
	}

}
