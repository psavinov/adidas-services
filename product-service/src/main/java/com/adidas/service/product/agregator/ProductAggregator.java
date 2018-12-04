package com.adidas.service.product.agregator;

import com.adidas.service.product.entity.Data;
import com.adidas.service.product.entity.Product;

/**
 * @author Pavel Savinov
 */
public interface ProductAggregator {

	public Product agregateData(Data... data);

}
