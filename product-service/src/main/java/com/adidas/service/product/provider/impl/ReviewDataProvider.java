package com.adidas.service.product.provider.impl;

import com.adidas.service.product.entity.Data;
import com.adidas.service.product.entity.ProductKeys;
import com.adidas.service.product.entity.ReviewData;
import com.adidas.service.product.provider.DataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Savinov
 */
@Service("review")
public class ReviewDataProvider implements DataProvider {

	@Override
	public Data getData(Serializable resourceId) {
		RestTemplate restTemplate = new RestTemplate();

		Map<String, Object> map = new HashMap<>();

		ReviewData reviewData = new ReviewData(0, 0d);

		try {
			map = restTemplate.getForObject(
				String.format("%s%s", endpoint, resourceId), Map.class);

			reviewData = new ReviewData(
				Integer.valueOf(
					map.getOrDefault("reviewsNumber", "0").toString()),
				Double.valueOf(
					map.getOrDefault("averageScore", "0").toString()));

			return reviewData;
		}
		catch (Exception e) {
			logger.error("Unable to get data from the Review Service", e);
		}

		reviewData.put(
			ProductKeys.error_message.name(),
			"Unable to get data from the Review Service");

		return reviewData;
	}

	private Logger logger = LoggerFactory.getLogger(ReviewDataProvider.class);

	@Value("${reviews.endpoint}")
	private String endpoint;

}
