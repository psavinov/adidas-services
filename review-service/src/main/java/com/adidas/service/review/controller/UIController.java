package com.adidas.service.review.controller;

import com.adidas.service.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Pavel Savinov
 */
@Controller
@RequestMapping("/reviews-manager")
public class UIController {

	@GetMapping
	public String reviews(Map<String, Object> model, HttpServletResponse response) {
		response.setHeader("X-Frame-Options", "allow");

		model.put("count", reviewService.getReviewsCount());

		return "reviews";
	}

	@Autowired
	private ReviewService reviewService;

}
