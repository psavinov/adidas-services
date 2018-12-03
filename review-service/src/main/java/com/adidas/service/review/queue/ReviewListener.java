package com.adidas.service.review.queue;

import com.adidas.service.review.entity.Review;
import com.adidas.service.review.exception.InvalidReviewException;
import com.adidas.service.review.exception.NoSuchReviewException;
import com.adidas.service.review.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author Pavel Savinov
 */
@Component
public class ReviewListener {

	@JmsListener(destination = ActiveMQConfig.ADD_REVIEW_QUEUE)
	public void receiveAddReviewMessage(@Payload Review review) {
		try {
			reviewService.addReview(
				review.getProductId(), review.getReviewsNumber(),
				review.getAverageScore());
		}
		catch(InvalidReviewException ire) {
			logger.error(ire.getMessage());
		}
	}

	@JmsListener(destination = ActiveMQConfig.DELETE_REVIEW_QUEUE)
	public void receiveDeleteReviewMessage(@Payload String productId) {
		reviewService.deleteReview(productId);
	}

	@JmsListener(destination = ActiveMQConfig.UPDATE_REVIEW_QUEUE)
	public void receiveUpdateReviewMessage(@Payload Review review) {
		try {
			reviewService.updateReview(
				review.getProductId(), review.getReviewsNumber(),
				review.getAverageScore());
		}
		catch (NoSuchReviewException nsre) {
			if (logger.isDebugEnabled()) {
				logger.debug(
					"Updating review that doesn't exist: " + review.toString());
			}
		}
		catch (InvalidReviewException ire) {
			logger.error(ire.getMessage());
		}
	}

	@Autowired
	private ReviewService reviewService;

	private Logger logger = LoggerFactory.getLogger(ReviewListener.class);

}
