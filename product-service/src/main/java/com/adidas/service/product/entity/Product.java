package com.adidas.service.product.entity;

import java.util.EnumMap;

/**
 * @author Pavel Savinov
 */
public class Product extends EnumMap<ProductKeys, Object> {

	public Product() {
		super(ProductKeys.class);
	}

}
