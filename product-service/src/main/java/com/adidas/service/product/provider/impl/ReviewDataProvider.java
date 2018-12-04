package com.adidas.service.product.provider.impl;

import com.adidas.service.product.entity.Data;
import com.adidas.service.product.entity.ReviewData;
import com.adidas.service.product.provider.DataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Pavel Savinov
 */
@Service("review")
public class ReviewDataProvider implements DataProvider {

	@Override
	public Data getData(Serializable resourceId) {
		RestTemplate restTemplate = new RestTemplate();

		Map<String, Object> map = restTemplate.getForObject(
			String.format("%s%s", endpoint, resourceId), Map.class);

		ReviewData reviewData = new ReviewData(
			Integer.valueOf(map.getOrDefault("reviewsNumber", "0").toString()),
			Double.valueOf(map.getOrDefault("averageScore", "0").toString()));

		return reviewData;
	}

	@Value("${reviews.endpoint}")
	private String endpoint;

}
