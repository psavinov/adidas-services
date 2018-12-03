package com.adidas.service.review.service.impl;

import com.adidas.service.review.dao.ReviewDao;
import com.adidas.service.review.entity.Review;
import com.adidas.service.review.exception.InvalidReviewException;
import com.adidas.service.review.exception.NoSuchReviewException;
import com.adidas.service.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pavel Savinov
 */
@Service
public class ReviewServiceImpl implements ReviewService {

	@Override
	public Review addReview(
			String productId, Integer reviewsNumber, Double averageScore)
		throws InvalidReviewException{

		validate(productId, reviewsNumber, averageScore);

		Review review = new Review();

		review.setProductId(productId);
		review.setReviewsNumber(reviewsNumber);
		review.setAverageScore(averageScore);

		return reviewDao.save(review);
	}

	@Override
	public void deleteReview(String productId) {
		reviewDao.deleteById(productId);
	}

	@Override
	public List<Review> getReviews(Pageable pageable) {
		Page<Review> page = reviewDao.findAll(pageable);

		return page.get().collect(Collectors.toList());
	}

	@Override
	public Review getReviewByProductId(String productId)
		throws NoSuchReviewException {

		return reviewDao.findById(productId).orElseThrow(
			NoSuchReviewException::new);
	}

	@Override
	public Review updateReview(
			String productId, Integer reviewsNumber, Double averageScore)
		throws NoSuchReviewException, InvalidReviewException {

		validate(productId, reviewsNumber, averageScore);

		Review review = reviewDao.findById(productId).orElseThrow(
			NoSuchReviewException::new);

		review.setReviewsNumber(reviewsNumber);
		review.setAverageScore(averageScore);

		return reviewDao.save(review);
	}

	private void validate(
			String productId, Integer reviewsNumber, Double averageScore)
		throws InvalidReviewException {

		if (productId == null || productId.equals("") ||
			reviewsNumber == null || averageScore == null) {

			throw new InvalidReviewException(
				productId, reviewsNumber, averageScore,
				"Arguments must be not null");
		}

		if (reviewsNumber <= 0) {
			throw new InvalidReviewException(
				productId, reviewsNumber, averageScore,
				"Reviews number must be greater than zero");
		}

		if (averageScore < 0) {
			throw new InvalidReviewException(
				productId, reviewsNumber, averageScore,
				"Reviews number must be greater or equal to zero");
		}
	}

	@Autowired
	private ReviewDao reviewDao;

}
