package com.adidas.service.review.exception;

import com.adidas.service.review.entity.Review;

/**
 * Exception to be thrown when some {@link Review} properties are incorrect.
 *
 * @author Pavel Savinov
 */
public class InvalidReviewException extends Exception {

	public InvalidReviewException(String message, Review review) {
		super(String.format("%s %s", message, review.toString()));
	}

	public InvalidReviewException(
		String productId, Integer reviewsNumber, Double averageScore,
		String message) {

		this(message, new Review(productId, reviewsNumber, averageScore));
	}
}
