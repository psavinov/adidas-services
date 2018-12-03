/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.adidas.service.review.controller;

import com.adidas.service.review.entity.Review;
import com.adidas.service.review.exception.NoSuchReviewException;
import com.adidas.service.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
		@ApiResponse(code = 200, message = "Review added successfully"),
		@ApiResponse(code = 401, message = "You are not authorized to add a review"),
		@ApiResponse(code = 500, message = "Incorrect review data")
	})
	@PostMapping
	public Review addReview(@RequestBody Review review) {
		return reviewService.addReview(
			review.getProductId(), review.getReviewsNumber(),
			review.getAverageScore());
	}

	@ApiOperation(
		value = "Delete a review",
		authorizations = {@Authorization(value="basicAuth")}
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Review deleted successfully"),
		@ApiResponse(code = 401, message = "You are not authorized to delete a review")
	})
	@DeleteMapping(path = "/{productId}")
	public void deleteReview(@PathVariable String productId) {
		reviewService.deleteReview(productId);
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
		@ApiResponse(code = 200, message = "Review updated successfully"),
		@ApiResponse(code = 401, message = "You are not authorized to update a review"),
		@ApiResponse(code = 500, message = "Incorrect review data")
	})
	@PutMapping
	public Review updateReview(@RequestBody Review review)
		throws NoSuchReviewException {

		return reviewService.updateReview(
			review.getProductId(), review.getReviewsNumber(),
			review.getAverageScore());
	}

	@Autowired
	private ReviewService reviewService;

}
