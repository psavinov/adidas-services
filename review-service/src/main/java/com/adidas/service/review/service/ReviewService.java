package com.adidas.service.review.service;

import com.adidas.service.review.entity.Review;
import com.adidas.service.review.exception.InvalidReviewException;
import com.adidas.service.review.exception.NoSuchReviewException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Review service, provides an abstraction to work with {@link Review} instances.
 *
 * @author Pavel Savinov
 */
public interface ReviewService {

	/**
	 * Adds a new Review.
	 *
	 * @param productId Product ID
	 * @param reviewsNumber Number of reviews
	 * @param averageScore Average reviews score
	 * @return Review
	 * @throws InvalidReviewException In case when some Review properties are
	 * incorrect.
	 */
	public Review addReview(
			String productId, Integer reviewsNumber, Double averageScore)
		throws InvalidReviewException;

	/**
	 * Deletes a Review.
	 *
	 * @param productId Product ID
	 */
	public void deleteReview(String productId);

	/**
	 * Gets a list of available Review instances.
	 *
	 * @param pageable Pagination parameters.
	 * @return List of Review instances.
	 */
	public List<Review> getReviews(Pageable pageable);

	/**
	 * Get a Review by product ID. Cached.
	 *
	 * @param productId Product ID
	 * @return A Review instance corresponding to the Product ID.
	 * @throws NoSuchReviewException In case when there is no such Review.
	 */
	@Cacheable("reviews")
	public Review getReviewByProductId(String productId)
		throws NoSuchReviewException;

	/**
	 * Updates a new Review.
	 *
	 * @param productId Product ID
	 * @param reviewsNumber Number of reviews
	 * @param averageScore Average reviews score
	 * @return Review
	 * @throws InvalidReviewException In case when some Review properties are
	 * incorrect.
	 */
	public Review updateReview(
			String productId, Integer reviewsNumber, Double averageScore)
		throws NoSuchReviewException, InvalidReviewException;

}
