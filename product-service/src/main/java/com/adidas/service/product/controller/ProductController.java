package com.adidas.service.product.controller;

import com.adidas.service.product.agregator.ProductAggregator;
import com.adidas.service.product.entity.Data;
import com.adidas.service.product.entity.Product;
import com.adidas.service.product.entity.ProductKeys;
import com.adidas.service.product.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Pavel Savinov
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/{productId}")
	public Product getProduct(
		@PathVariable String productId, HttpServletResponse response) {

		Data reviewData = reviewDataProvider.getData(productId);
		Data productData = productDataProvider.getData(productId);

		if (reviewData.containsKey(ProductKeys.error_message.name()) &&
			productData.containsKey(ProductKeys.error_message.name())) {

			productData.merge(
				ProductKeys.error_message.name(),
				reviewData.get(ProductKeys.error_message.name()),
				(oldValue, newValue) ->
					String.format("%s, %s", oldValue, newValue));

			response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);

			return productAggregator.agregateData(productData);
		}

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
