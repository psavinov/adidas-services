package com.adidas.service.review.controller;

import com.adidas.service.review.entity.Review;
import com.adidas.service.review.exception.NoSuchReviewException;
import com.adidas.service.review.queue.ActiveMQConfig;
import com.adidas.service.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pavel Savinov
 */
@Api(value="Review Service API", description="CRUD operations for review service")
@RestController
@RequestMapping("/review")
public class ReviewController {

	@ApiOperation(
		value = "Add a review",
		authorizations = {@Authorization(value="basicAuth")}
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Review successfully added to the creation queue"),
		@ApiResponse(code = 401, message = "You are not authorized to add a review")
	})
	@PostMapping
	public Review addReview(@RequestBody Review review) {
		jmsTemplate.convertAndSend(ActiveMQConfig.ADD_REVIEW_QUEUE, review);

		return review;
	}

	@ApiOperation(
		value = "Delete a review",
		authorizations = {@Authorization(value="basicAuth")}
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Review successfully added to the delete queue"),
		@ApiResponse(code = 401, message = "You are not authorized to delete a review")
	})
	@DeleteMapping(path = "/{productId}")
	public void deleteReview(@PathVariable String productId) {
		jmsTemplate.convertAndSend(
			ActiveMQConfig.DELETE_REVIEW_QUEUE, productId);
	}

	@ApiOperation(value = "Retrieve a review by product ID")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved review")
	})
	@GetMapping(path = "/{productId}")
	public Review getReview(@PathVariable String productId)
		throws NoSuchReviewException {

		return reviewService.getReviewByProductId(productId);
	}

	@ApiOperation(value = "Retrieve a list of reviews")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list")
	})
	@GetMapping
	public List<Review> getReviews(Pageable pageable) {
		return reviewService.getReviews(pageable);
	}

	@ApiOperation(
		value = "Update a review",
		authorizations = {@Authorization(value="basicAuth")}
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Review successfully added to the update queue"),
		@ApiResponse(code = 401, message = "You are not authorized to update a review")
	})
	@PutMapping
	public Review updateReview(@RequestBody Review review) {
		jmsTemplate.convertAndSend(ActiveMQConfig.UPDATE_REVIEW_QUEUE, review);

		return review;
	}

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ReviewService reviewService;

}
