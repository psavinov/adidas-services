package com.adidas.service.review.controller;

import com.adidas.service.review.entity.Review;
import com.adidas.service.review.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Pavel Savinov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
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

		review.put("productId", "BB5476");
		review.put("reviewsNumber", 65);
		review.put("averageScore", 4.12);

		this.mockMvc.perform(
			get("/review/BB5476")
		).andExpect(content().json(objectMapper.writeValueAsString(review)));
	}

	@Test
	public void testGetReviews() throws Exception {
		Map<String, Object> review = new HashMap<>();

		review.put("productId", "BB5476");
		review.put("reviewsNumber", 65);
		review.put("averageScore", 4.12);

		this.mockMvc.perform(
			get("/review")
		).andExpect(jsonPath("$.length()").value(6));
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void testUpdateReview() throws Exception {
		Map<String, Object> review = new HashMap<>();

		review.put("productId", "1234PR");
		review.put("reviewsNumber", 5);
		review.put("averageScore", 2.56);

		this.mockMvc.perform(
			put("/review")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(review)))
			.andExpect(status().isOk());
	}

}
