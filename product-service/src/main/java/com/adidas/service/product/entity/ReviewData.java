package com.adidas.service.product.entity;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Pavel Savinov
 */
public class ReviewData
	extends ConcurrentHashMap<String, Object> implements Data  {

	public ReviewData(Integer reviewsNumber, Double averageScore) {
		super();

		put("reviews_number", reviewsNumber);
		put("average_score", averageScore);
	}

}
