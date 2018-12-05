package com.adidas.service.review.controller;

import com.adidas.service.review.entity.Review;
import com.adidas.service.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pavel Savinov
 */
@RestController
@RequestMapping("/reviews-data")
public class UIDataController {

	@GetMapping
	public List<Review> getReviews(@RequestParam("term") String term) {
		return reviewService.getReviews(term);
	}

	@Autowired
	private ReviewService reviewService;

}
