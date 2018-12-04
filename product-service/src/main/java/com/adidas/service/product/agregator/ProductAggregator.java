package com.adidas.service.product.agregator;

import com.adidas.service.product.entity.Data;
import com.adidas.service.product.entity.Product;

/**
 * Defines a product data aggregator, an abstraction which allows to aggregate
 * {@link Data} instances from different sources and compose a {@link Product}
 * instance.
 *
 * @author Pavel Savinov
 */
public interface ProductAggregator {

	public Product agregateData(Data... data);

}
