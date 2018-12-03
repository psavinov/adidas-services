package com.adidas.service.review.controller;

import com.adidas.service.review.entity.Review;
import com.adidas.service.review.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Pavel Savinov
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

	@Before
	public void setUp() throws Exception {
		Review review = new Review();

		review.setProductId("1234PR");
		review.setReviewsNumber(5);
		review.setAverageScore(2.56);

		Mockito.when(reviewService.addReview("1234PR", 5, 2.56))
			.thenReturn(review);

		Mockito.when(reviewService.updateReview("1234PR", 5, 2.56))
			.thenReturn(review);

		Mockito.when(reviewService.getReviewByProductId("1234PR"))
			.thenReturn(review);

		Mockito.when(reviewService.getReviews(PageRequest.of(0, 20)))
			.thenReturn(Collections.singletonList(review));
	}

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReviewService reviewService;

	@MockBean
	private AuthenticationEntryPoint authenticationEntryPoint;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testAddReview() throws Exception {
		Map<String, Object> review = new HashMap<>();

		review.put("productId", "1234PR");
		review.put("reviewsNumber", 5);
		review.put("averageScore", 2.56);

		this.mockMvc.perform(
			post("/review")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(review))
		).andExpect(status().isOk());
	}

	@Test
	public void testGetReview() throws Exception {
		Map<String, Object> review = new HashMap<>();

		review.put("productId", "1234PR");
		review.put("reviewsNumber", 5);
		review.put("averageScore", 2.56);

		this.mockMvc.perform(
			get("/review/1234PR")
		).andExpect(content().json(objectMapper.writeValueAsString(review)));
	}

	@Test
	public void testGetReviews() throws Exception {
		Map<String, Object> review = new HashMap<>();

		review.put("productId", "1234PR");
		review.put("reviewsNumber", 5);
		review.put("averageScore", 2.56);

		this.mockMvc.perform(
			get("/review")
		).andExpect(content().json(
			objectMapper.writeValueAsString(
				Collections.singletonList(review))));
	}

	@Test
	public void testUpdateReview() throws Exception {
		Map<String, Object> review = new HashMap<>();

		review.put("productId", "1234PR");
		review.put("reviewsNumber", -1);
		review.put("averageScore", 2.56);

		this.mockMvc.perform(
			put("/review")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(review)))
			.andExpect(status().isOk());
	}

}
