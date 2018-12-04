package com.adidas.service.product.entity;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of {@link Data} which represents a review data based on the
 * Reviews service response.
 *
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
