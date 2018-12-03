package com.adidas.service.review.service;

import com.adidas.service.review.entity.Review;
import com.adidas.service.review.exception.NoSuchReviewException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Pavel Savinov
 */
public interface ReviewService {

	public Review addReview(
		String productId, Integer reviewsNumber, Double averageScore);

	public void deleteReview(String productId);

	public List<Review> getReviews(Pageable pageable);

	@Cacheable("reviews")
	public Review getReviewByProductId(String productId)
		throws NoSuchReviewException;

	public Review updateReview(
			String productId, Integer reviewsNumber, Double averageScore)
		throws NoSuchReviewException;

}
