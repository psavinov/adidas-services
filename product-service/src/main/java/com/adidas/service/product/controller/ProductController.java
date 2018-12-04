package com.adidas.service.product.controller;

import com.adidas.service.product.agregator.ProductAggregator;
import com.adidas.service.product.entity.Data;
import com.adidas.service.product.entity.Product;
import com.adidas.service.product.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Savinov
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/{productId}")
	public Product getProduct(@PathVariable String productId) {
		Data reviewData = reviewDataProvider.getData(productId);
		Data productData = productDataProvider.getData(productId);

		return productAggregator.agregateData(productData, reviewData);
	}

	@Autowired
	@Qualifier("adidasECom")
	private DataProvider productDataProvider;

	@Autowired
	@Qualifier("review")
	private DataProvider reviewDataProvider;

	@Autowired
	private ProductAggregator productAggregator;

}
