package com.adidas.service.product.agregator.impl;

import com.adidas.service.product.agregator.ProductAggregator;
import com.adidas.service.product.entity.Data;
import com.adidas.service.product.entity.Product;
import com.adidas.service.product.entity.ProductKeys;
import org.springframework.stereotype.Service;

/**
 * @author Pavel Savinov
 */
@Service
public class ProductAggregatorImpl implements ProductAggregator {

	@Override
	public Product agregateData(Data... dataArray) {

		Product product = new Product();

		for (Data data : dataArray) {
			data.forEach(
				(key, value) -> {
					ProductKeys productKey = ProductKeys.valueOf(key);

					if (productKey == null) {
						return;
					}

					product.put(productKey, data.get(productKey.name()));
				}
			);
		}

		return product;
	}

}
