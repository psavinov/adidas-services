package com.adidas.service.review.exception;

import com.adidas.service.review.entity.Review;

/**
 * @author Pavel Savinov
 */
public class InvalidReviewException extends Exception {

	public InvalidReviewException(String message, Review review) {
		super(message + review.toString());
	}

	public InvalidReviewException(
		String productId, Integer reviewsNumber, Double averageScore,
		String message) {

		this(message, new Review(productId, reviewsNumber, averageScore));
	}
}
